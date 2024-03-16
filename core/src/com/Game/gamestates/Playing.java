package com.Game.gamestates;

import com.Game.Entities.Enemy;
import com.Game.Entities.Player;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.Game.gamestates.Menu.controls;

public class Playing implements Screen {
    private Music bgm;
    private myGdxGame game;
    private Player player;
    private boolean paused;
    private float timePassed = 0; // 5 minutes in seconds
    private String timerText;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    public Playing(myGdxGame game){
        this.game = game;
        player = new Player(500, new Texture("Sprites/bullet.png"), 0, 0, this);
        enemies.add(new Enemy(500, new Texture("Sprites/bullet.png"), 300, 300, this));
        game.font.setColor(Color.CYAN);
        game.font.getData().setScale(5f);
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
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(255, 255, 255, 0);
        controls();
        game.batch.begin();
        getTime();
        player.draw();
        for (Enemy enemy: enemies){
            enemy.draw(player.position);
        }
        game.batch.end();
    }

    public void getTime(){
        float deltaTime = Gdx.graphics.getDeltaTime();
        timePassed += deltaTime;
        int minutes = (int) (timePassed / 60);
        int seconds = (int) (timePassed % 60);
        // Display the timer
        timerText = String.format("%02d:%02d", minutes, seconds);
        game.font.draw(game.batch, timerText, (float) Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight());
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
        game.font.dispose();
        game.dispose();
    }

    public myGdxGame getGame() {
        return game;
    }
}
