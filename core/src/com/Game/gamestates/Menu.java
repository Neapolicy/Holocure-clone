package com.Game.gamestates;

import com.Game.myGdxGame;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Menu implements Screen {
    private Music bgm;
    private myGdxGame game;
    public Menu(myGdxGame game){
        this.game = game;
        System.out.println("yo");
        new Playing(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
        game.dispose();
    }
}
