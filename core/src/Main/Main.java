package Main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Music bgm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Sprites/badlogic.jpg");
		bgm = Gdx.audio.newMusic(Gdx.files.internal("Audio/SFX/gun_fire.wav"));
		bgm.play();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		bgm.dispose();
	}
}
