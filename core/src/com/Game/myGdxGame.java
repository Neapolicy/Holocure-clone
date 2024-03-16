package com.Game;

import com.Game.Animation.Animator;
import java.util.ArrayList;

import com.Game.gamestates.Menu;
import com.Game.gamestates.Playing;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

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
