package com.Game;

import com.Game.Animation.Animator;
import java.util.ArrayList;

import com.Game.gamestates.Menu;
import com.Game.gamestates.Playing;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class myGdxGame extends Game implements Runnable{
	private SpriteBatch batch;
	public static ArrayList<Animator> animate = new ArrayList<>();
	public static ArrayList<Texture> img = new ArrayList<>();
	private Music bgm;
	public myGdxGame(){
		new Menu();
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 255, 255, 1);
		batch.begin();
		for (Texture img : img){
			batch.draw(img, 0, 0);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Texture img : img){
			img.dispose();
		}
		for (Animator animate : animate){
			animate.dispose();
		}
		bgm.dispose();
	}

	@Override
	public void run() {

	}
}
