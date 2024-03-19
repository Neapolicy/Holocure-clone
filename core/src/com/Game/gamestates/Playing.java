package com.Game.gamestates;

import com.Game.Entities.Enemy;
import com.Game.Entities.Player;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.awt.*;
import java.util.ArrayList;

import static com.Game.gamestates.Menu.controls;

public class Playing implements Screen {
    public static Texture backgroundTexture = new Texture("Backgrounds/playing_bg.png");
    private Music bgm;
    private myGdxGame game;
    private Player player;
    private OrthographicCamera camera = new OrthographicCamera();
    private ExtendViewport viewport;
    public static float timePassed = 0; // 5 minutes in seconds
    private Texture timerText;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    public Playing(myGdxGame game){
        this.game = game;
        player = new Player(500, new Texture("Sprites/bullet.png"), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, game);
        enemies.add(new Enemy(500, new Texture("Sprites/bullet.png"), 300, 300, game));
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
//        musicMan();
    }

    public void musicMan(){
        bgm = Gdx.audio.newMusic(Gdx.files.internal("Audio/Music/combat_music.wav"));
        bgm.setVolume(.3f);
        bgm.setLooping(true);
        bgm.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        controls();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        camera.position.set(player.sprite.getX() / 2, player.sprite.getY() / 2, 0);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
//        getTime();
        game.batch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        player.draw();
//        for (Enemy enemy: enemies){
//            enemy.draw(player.position);
//        }
        game.batch.end();
    }

    public void getTime(){
        float deltaTime = Gdx.graphics.getDeltaTime();
        timePassed += deltaTime;
        int minutes = (int) (timePassed / 60);
        int seconds = (int) (timePassed % 60);
        timerText = new Texture(String.format("%02d:%02d", minutes, seconds));
        // Display the timer
        game.batch.draw(timerText, 100, 100);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
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
        game.font.dispose();
        game.dispose();
    }

    public myGdxGame getGame() {
        return game;
    }
}
