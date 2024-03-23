package com.Game.Utils;

import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
public class Hud implements Disposable{

    //Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    private Viewport viewport;
    public static float timePassed;
    private int minutes, seconds;

    //Scene2D widgets
    private Label countdownLabel;
    private Label timeLabel;

    public Hud(SpriteBatch sb, myGdxGame game){
        //define our tracking variables

        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        //define our labels using the String, and a Label style consisting of a font and color
        countdownLabel = new Label(String.format("%02d:%02d", minutes, seconds), new Label.LabelStyle(game.font24, Color.WHITE));
        timeLabel = new Label("STAGE", new Label.LabelStyle(game.font24, Color.WHITE));

        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(timeLabel).expandX().padTop(10);
        //add a second row to our table
        table.row();
        table.add(countdownLabel).expandX();

        //add our table to the stage
        stage.addActor(table);
    }

    public void update(){
        float deltaTime = Gdx.graphics.getDeltaTime();

        timePassed += deltaTime;

        minutes = (int) (timePassed / 60);
        seconds = (int) (timePassed % 60);
        countdownLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }
    @Override
    public void dispose() { stage.dispose(); }

}