package com.Game.gamestates;

import com.Game.Entities.Player;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Selection implements Screen { //pick your weapon here
    private myGdxGame game;
    private Stage stage;
    private TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(); //find button skin
    private ImageButton buttonOne, buttonTwo;
    public Selection(myGdxGame game){
        this.game = game;
        Viewport viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, game.batch);
        textButtonStyle.font = game.font24; //redo the freetypefont generation
        textButtonStyle.fontColor = Color.WHITE;
        makeButton();
        makeSecondButton();
        Gdx.input.setInputProcessor(stage);
    }
    public void makeButton(){
        Texture myTexture = new Texture(Gdx.files.internal("Sprites/spear.png")); //improve it later ig, I can change the texture size later
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        buttonOne = new ImageButton(myTexRegionDrawable);
        buttonOne.setPosition((float) ((Gdx.graphics.getWidth() / 2) - buttonOne.getWidth() * 1.5), (float) (Gdx.graphics.getHeight() / 3));
        buttonOne.setSize(300, 500);
        stage.addActor(buttonOne);
    }
    public void makeSecondButton(){
        Texture myTexture = new Texture(Gdx.files.internal("Sprites/axe.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        buttonTwo = new ImageButton(myTexRegionDrawable);
        buttonTwo.setPosition((float) ((Gdx.graphics.getWidth() / 2) + buttonTwo.getWidth() * 1.5), (float) (Gdx.graphics.getHeight() / 3));
        stage.addActor(buttonTwo);
    }
    public void update(){
        if (buttonOne.isPressed()){
            Player.weaponChoice = "spear";
            game.setScreen(new Playing(game));
        }
        if (buttonTwo.isPressed()){
            Player.weaponChoice = "sword";
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
        game.batch.begin();
        game.fontArcon.draw(game.batch, "Pick Your Weapon", ((float) Gdx.graphics.getWidth() / 2) - 150, Gdx.graphics.getHeight());
        game.fontArcon.draw(game.batch, "Fire Ax", buttonTwo.getX(), buttonTwo.getY());
        game.fontArcon.draw(game.batch, "Flame Spear", buttonOne.getX(), buttonOne.getY());
        game.batch.end();
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
