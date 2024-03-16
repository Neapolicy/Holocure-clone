package com.Game.gamestates;

import com.Game.Animation.Animator;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
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
        animator.createAnimation("Sprites/sprite_sheet_walk.png");
    }
    public void update(){
        if (Gdx.input.isButtonJustPressed(0)){
            game.setScreen(new Playing(game));
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
        game.dispose();
    }
}
