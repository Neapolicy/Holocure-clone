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

import java.awt.*;
import java.util.ArrayList;

import static com.Game.gamestates.Menu.controls;

public class Playing implements Screen {
    public static Texture backgroundTexture = new Texture("Backgrounds/playing_bg.png");
    private Music bgm;
    private myGdxGame game;
    private Player player;
    private OrthographicCamera camera = new OrthographicCamera();
    public static float timePassed = 0; // 5 minutes in seconds
    private Texture timerText;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    public Playing(myGdxGame game){
        this.game = game;
        player = new Player(500, new Texture("Sprites/player_idle.png"), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, game);
        enemies.add(new Enemy(500, new Texture("Sprites/bullet.png"), 300, 300, game));
        camera.setToOrtho(false, 100, 100);
        camera.zoom += 10f;
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
        game.batch.begin();
//        camera.position.set(player.sprite.getX(), player.sprite.getY(), 0);
//        camera.update();
//        getTime();
        game.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
