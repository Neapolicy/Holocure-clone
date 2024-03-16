package com.Game.gamestates;

import com.Game.Entities.Enemy;
import com.Game.Entities.Player;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class Playing implements Screen {
    private Music bgm;
    private myGdxGame game;
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    public Playing(myGdxGame game){
        this.game = game;
        player = new Player(500, new Texture("Sprites/bullet.png"), 0, 0, this);
        enemies.add(new Enemy(500, new Texture("Sprites/bullet.png"), 300, 300, player.position, this));
        musicMan();
    }

    public void musicMan(){
        bgm = Gdx.audio.newMusic(Gdx.files.internal("Audio/Music/combat_music.wav"));
        bgm.setVolume(.5f);
        bgm.setLooping(true);
        bgm.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(255, 255, 255, 0);
        game.batch.begin();
        player.draw();
        for (Enemy enemy: enemies){
            enemy.draw();
        }
        player.getAnimator().render((int) player.position.x, (int) player.position.y);
        game.batch.end();
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
        game.dispose();
    }

    public myGdxGame getGame() {
        return game;
    }
}
