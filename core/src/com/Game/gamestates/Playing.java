package com.Game.gamestates;

import com.Game.Entities.Enemy;
import com.Game.Entities.Player;
import com.Game.Utils.CameraStyles;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;

import static com.Game.gamestates.Menu.controls;

public class Playing implements Screen { //https://www.youtube.com/watch?v=Lb2vZ5lBgCY by connor the goat
    public static Texture backgroundTexture = new Texture("Backgrounds/playing_bg.png");
    private Music bgm;
    private myGdxGame game;
    private Player player;
    private OrthographicCamera camera;
    private ExtendViewport viewport;
    public static float timePassed = 0;
    private String timerText;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    public Playing(myGdxGame game){ //to make the background work, i need to use a tile map editor
        this.game = game;
         //use this video for reference https://www.youtube.com/watch?v=WRS9SC0i0oc&list=PLZm85UZQLd2SXQzsF-a0-pPF6IWDDdrXt&index=6

        initilizeEntities();

        game.font24.setColor(Color.WHITE);

//        musicMan();
    }
    public void initilizeEntities(){
        player = new Player(150, new Texture("Sprites/bullet.png"), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, game);
        enemies.add(new Enemy(500, new Texture("Sprites/bullet.png"), 300, 300, game));
    }

    public void musicMan(){
        bgm = Gdx.audio.newMusic(Gdx.files.internal("Audio/Music/combat_music.wav"));
        bgm.setVolume(.3f);
        bgm.setLooping(true);
        bgm.play();
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("Backgrounds/Stage.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
    }

    @Override
    public void render(float delta) { //https://www.youtube.com/watch?v=zckxJn751Gw
        controls();

        System.out.println(player.position.x);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        renderer.setView(camera);
        renderer.render();

        cameraUpdate();

        player.draw();

        getTime();

//        for (Enemy enemy: enemies){
//            enemy.draw(player.position);
//        }
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.end();
    }
    public void cameraUpdate(){
        CameraStyles.lockOnTarget(camera, player.position);
    }

    public void getTime(){
        float deltaTime = Gdx.graphics.getDeltaTime();
        timePassed += deltaTime;
        int minutes = (int) (timePassed / 60);
        int seconds = (int) (timePassed % 60);
        timerText = String.format("%02d:%02d", minutes, seconds);
        // Display the timer
        game.font24.draw(game.batch, timerText, player.position.x - 50, player.position.y + 500);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
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
        backgroundTexture.dispose();
        game.font24.dispose();
        game.dispose();
        map.dispose();
        renderer.dispose();
    }

    public myGdxGame getGame() {
        return game;
    }
}
