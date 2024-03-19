package com.Game.Utils;

import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator{
    // Constant rows and columns of the sprite sheet
    private int FRAME_COLS = 6, FRAME_ROWS = 1;
    private myGdxGame game;

    // Objects used
    private Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    private Texture walkSheet;
    private SpriteBatch spriteBatch;

    // A variable for tracking elapsed time for the animation
    private float stateTime;

    public void createAnimation(Texture walkSheet, myGdxGame game) {

        // Load the sprite sheet as a Texture
        this.walkSheet = walkSheet;
        this.game = game;

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        // Initialize the Animation with the frame interval and array of frames
        walkAnimation = new Animation<TextureRegion>(0.12f, walkFrames);

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }
    public void changeColnRows(int col, int row){
        FRAME_COLS = col;
        FRAME_ROWS = row;
    }

    public void render(int x, int y) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        game.batch.draw(currentFrame, x, y); // Draw current frame at (50, 50)
    }

    public void dispose() { // SpriteBatches and Textures must always be disposed
        spriteBatch.dispose();
        walkSheet.dispose();
    }
}
