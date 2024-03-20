package com.Game;

import com.Game.Utils.Animator;

import com.Game.gamestates.Menu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class myGdxGame extends Game{
	public SpriteBatch batch;
	public BitmapFont font24;
	public Animator animator;

	@Override
	public void create () {
		animator = new Animator();
		batch = new SpriteBatch();

		initFonts();

		setScreen(new Menu(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		font24.dispose();
	}
	public void initFonts(){
		try{
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Archon.ttf"));
			FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

			params.size = 24;
			params.color = Color.BLACK;
			font24 = generator.generateFont(params);
			generator.dispose();
		} catch (Exception e) {
			System.out.println("no");
        }
    }
}
