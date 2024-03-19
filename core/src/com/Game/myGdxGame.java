package com.Game;

import com.Game.Utils.Animator;

import com.Game.gamestates.Menu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class myGdxGame extends Game{
	public SpriteBatch batch;
	public BitmapFont font;
	public Animator animator;
	
	@Override
	public void create () {
		animator = new Animator();
		batch = new SpriteBatch();
		font = new BitmapFont();
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
	}
}
