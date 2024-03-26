package com.Game.gamestates;
import com.Game.Entities.Enemy;
import com.Game.Entities.Player;
import com.Game.Utils.CameraStyles;
import com.Game.Utils.Hud;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import java.util.ArrayList;
import static com.Game.gamestates.Menu.controls;
public class Playing implements Screen { //https://www.youtube.com/watch?v=Lb2vZ5lBgCY by connor the goat
    public static OrthographicCamera camera;
    public static float levelWidth, levelHeight;
    private Music bgm;
    private myGdxGame game;
    private Player player;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Hud hud;
    public Playing(myGdxGame game) {
        this.game = game; //https://www.youtube.com/watch?v=xQXOC74oeSU recent video on box2d
        makeMap();
        initilizeEntities();
        game.font24.setColor(Color.WHITE);
        hud = new Hud(game.batch, game);
//        musicMan();
    }
    public void initilizeEntities() {
        player = new Player(500, new Texture("Sprites/player_idle.png"), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, game);
        enemies.add(new Enemy(300, new Texture("Sprites/bullet.png"), 300, 300, game));
    }
    public void musicMan() { //turned this off bc I don't want to hear this every time I playtest
        bgm = Gdx.audio.newMusic(Gdx.files.internal("Audio/Music/combat_music.wav"));
        bgm.setVolume(.3f);
        bgm.setLooping(true);
        bgm.play();
    }
    public void makeMap() {
        map = new TmxMapLoader().load("Backgrounds/Stage.tmx"); //it could be the stagesheet thing not having the transparent png but idk
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
    }

    @Override
    public void show() { //https://www.youtube.com/watch?v=zckxJn751Gw
        camera.zoom = 2 / 5f;
        player.setPosition(20, 20);
    }

    @Override
    public void render(float delta) {
        controls();
        hud.update();
        hud.healthCheck(player.currentHp);
        cameraUpdate();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Render the tilemap before starting the SpriteBatch
        game.batch.begin();
        renderer.render(); //place it below idk lol
        player.draw();
        for (Enemy enemy : enemies) {
            enemy.draw(player.position);
        }
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }
    public void cameraUpdate() { //renders the tile map and stuff
        renderer.setView(camera);
        CameraStyles.lockOnTarget(camera, player.position);
        game.batch.setProjectionMatrix(camera.combined);

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
    }
    public myGdxGame getGame() {
        return game;
    }
}