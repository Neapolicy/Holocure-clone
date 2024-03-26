package com.Game;

import com.Game.Utils.Animator;
import com.Game.gamestates.Menu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class myGdxGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font24;
    public BitmapFont fontArcon;
    public Animator animator;
    public static final float PPM = 100; //pixel per meter
    public static final int v_width = 400, v_height = 208;

    @Override
    public void create() {
        animator = new Animator();
        batch = new SpriteBatch();

        initFonts();

        setScreen(new Menu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        font24.dispose();
        animator.dispose();
    }

    public void initFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Adequate.ttf"));
        FreeTypeFontGenerator generatorTwo = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Arcon.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 48; // font size
        font24 = generator.generateFont(parameter);
        fontArcon = generatorTwo.generateFont(parameter);
        generator.dispose(); // avoid memory leaks, important
    }
}
