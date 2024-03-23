package com.Game.gamestates;

import com.Game.Utils.Animator;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Menu implements Screen {
    private Music bgm;
    private myGdxGame game;
    private Animator animator = new Animator();
    private Animator animatorTwo = new Animator();
    private Animator animatorThree = new Animator();

    public Menu(myGdxGame game){
        this.game = game;
        musicMan();
        animator.createAnimation(new Texture("Sprites/player_run.png"), game); //make sure that it matches
        animatorTwo.createAnimation(new Texture("Effects/slash_effect.png"), game);
        animatorThree.changeColnRows(5, 1);
        animatorThree.createAnimation(new Texture("Sprites/player_idle.png"), game);
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)){ // p to exit game, i want to make it a pause screen but it's low prio rn
            Gdx.app.exit();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(Playing.backgroundTexture, 300, 100);
        animatorThree.render(150, 100);
//        animator.render(100, 100);
//        animatorTwo.render(100, 200);
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
        animatorTwo.dispose();
        bgm.dispose();
        game.dispose();
    }
}
