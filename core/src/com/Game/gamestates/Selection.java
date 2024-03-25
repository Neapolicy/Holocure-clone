package com.Game.gamestates;

import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Selection implements Screen { //pick your weapon here
    private myGdxGame game;
    private Stage stage;
    private TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(); //find button skin

    public Selection(myGdxGame game){
        this.game = game;
        Viewport viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, game.batch);
        textButtonStyle.font = new BitmapFont(); //redo the freetypefont generation
        textButtonStyle.fontColor = Color.WHITE;
        stage.addActor(new TextButton("Custom Btn ", textButtonStyle));
        Gdx.input.setInputProcessor(stage);
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Menu.controls();
        update();
        stage.act();
        stage.draw();
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
        stage.dispose();
    }
}
