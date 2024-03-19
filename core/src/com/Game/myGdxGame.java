package com.Game;

import com.Game.Utils.Animator;

import com.Game.gamestates.Menu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class myGdxGame extends Game{
	public SpriteBatch batch;
	public BitmapFont font12;
	public Animator animator;
	public FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf")); //something to do with the fonts/myfonts
	public FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

	@Override
	public void create () {
		animator = new Animator();
		batch = new SpriteBatch();
//		font12 = generator.generateFont(parameter);;
		font12 = new BitmapFont();
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
		font12.dispose();
//		generator.dispose();
	}
}
