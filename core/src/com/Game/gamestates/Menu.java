package com.Game.gamestates;

import com.Game.Animation.Animator;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class Menu implements Screen {
    private Music bgm;
    private myGdxGame game;
    private Animator animator = new Animator();
    public Menu(myGdxGame game){
        this.game = game;
        musicMan();
        animator.createAnimation("Sprites/sprite_sheet_walk.png");
    }
    public void musicMan(){
        bgm = Gdx.audio.newMusic(Gdx.files.internal("Audio/Music/menu_music.wav"));
        bgm.setVolume(.3f);
        bgm.setLooping(true);
        bgm.play();
    }
    public void update(){
        if (Gdx.input.isButtonJustPressed(0)){
            bgm.stop();
            game.setScreen(new Playing(game));
        }
    }
    public static void controls(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)){ // p to exit game
            Gdx.app.exit();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();
        animator.render(0, 0);
        controls();
        update();
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
        animator.dispose();
        bgm.dispose();
        game.dispose();
    }
}
