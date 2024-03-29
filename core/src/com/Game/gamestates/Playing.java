package com.Game.gamestates;

import com.Game.Entities.Enemy;
import com.Game.Entities.Player;
import com.Game.Utils.CameraStyles;
import com.Game.Utils.Constants;
import com.Game.Utils.Hud;
import com.Game.Utils.TiledObjectUtil;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import static com.Game.Utils.Constants.PPM;
import static com.Game.gamestates.Menu.controls;
public class Playing implements Screen { //https://www.youtube.com/watch?v=Lb2vZ5lBgCY by connor the goat
    public static OrthographicCamera camera;
    public static float levelWidth, levelHeight;
    private final float scale = 2f;
    private Music bgm;
    private myGdxGame game;
    private Player player;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Hud hud;
    private World world;
    private Box2DDebugRenderer b2dr;

    public Playing(myGdxGame game) {
        this.game = game;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        hud = new Hud(game.batch, game);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / scale, h / scale);

        world = new World(new Vector2(0, 0), false); //vector is x + y gravity
        b2dr = new Box2DDebugRenderer();

        initilizeEntities();
        makeMap();

        TiledObjectUtil.parseObjectLayer(world, map.getLayers().get("Walls").getObjects());
        TiledObjectUtil.parseObjectLayer(world, map.getLayers().get("Tables").getObjects());
        //musicMan();
    }
    public void initilizeEntities() {
        player = new Player(500, new Texture("Sprites/player_idle.png"), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, game, world);
        enemies.add(new Enemy(300, new Texture("Sprites/bullet.png"), 300, 300, game, world));
    }
    public void musicMan() { //turned this off bc I don't want to hear this every time I playtest
        bgm = Gdx.audio.newMusic(Gdx.files.internal("Audio/Music/combat_music.wav"));
        bgm.setVolume(.3f);
        bgm.setLooping(true);
        bgm.play();
    }
    public void makeMap() {
        map = new TmxMapLoader().load("Backgrounds/Stage.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void show() { //https://www.youtube.com/watch?v=zckxJn751Gw
        camera.zoom = 2 / 5f;
    }

    @Override
    public void render(float delta) {
        update();

        hud.update();
        hud.healthCheck(player.currentHp);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        // Render the tilemap before starting the SpriteBatch
        game.batch.begin();

        player.draw();
        for (Enemy enemy : enemies) {
            enemy.draw(player.getPlayerBody().getPosition());
        }
        game.batch.end();

        b2dr.render(world, camera.combined.scl(PPM)); //draws the hitboxes
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        controls();
    }
    public void update(){
        world.step(1 / 60f, 6, 2); //6 and 2 is good practice

        cameraUpdate();

        renderer.setView(camera);
        game.batch.setProjectionMatrix(camera.combined);
    }
    public void cameraUpdate() { //renders the tile map and stuff
        CameraStyles.lockOnTarget(camera, player.getPlayerBody().getPosition());

        float startX = (camera.viewportWidth * 2 / 5) / 2;
        float startY = (camera.viewportHeight * 2 / 5) / 2;

        MapProperties properties = map.getProperties();

        levelWidth = properties.get("width", Integer.class);
        levelHeight = properties.get("height", Integer.class);
        CameraStyles.boundary(camera, startX, startY, levelWidth * 16 - startX * 2, levelHeight * 16 - startY * 2);
        camera.update();
    }
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
    }
    @Override
    public void dispose() {
        player.getAnimator().dispose();
        player.getWeapon().dispose();
        game.font24.dispose();
        game.dispose();
        map.dispose();
        renderer.dispose();
        hud.dispose();
        world.dispose();
        b2dr.dispose();
        bgm.dispose();
    }
    public myGdxGame getGame() {
        return game;
    }
}