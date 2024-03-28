package com.Game.Utils;

import com.Game.Entities.Entity;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator{
    // Constant rows and columns of the sprite sheet
    private int FRAME_COLS = 6, FRAME_ROWS = 1;
    private myGdxGame game;
    private TextureRegion currentFrame;

    // Objects used
    private Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    private Texture walkSheet;
    // A variable for tracking elapsed time for the animation
    private float stateTime;
    private Entity entity;
    public Animator(){}
    public Animator(Entity entity){ //if its a player/weapon, gives it the ability to move left and right
        this.entity = entity;
    }

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
        walkAnimation = new Animation<>(0.12f, walkFrames);
        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
        stateTime = 0f;
    }
    public void changeColnRows(int col, int row){
        FRAME_COLS = col;
        FRAME_ROWS = row;
    }

    public void render(int x, int y) {
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        // Get current frame of animation for the current stateTime
        if (entity != null){
            if (entity.isLeft() && !currentFrame.isFlipX()) {
                currentFrame.flip(true, false); // Flip horizontally
            } else if (!entity.isLeft() && currentFrame.isFlipX()) {
                currentFrame.flip(true, false); // Flip back to original
            }
        }
        game.batch.draw(currentFrame, x, y); // Draw current frame at (50, 50)
    }

    public Texture changeTextureSize(String filePath, int width, int height) {
        Pixmap pixmap200 = new Pixmap(Gdx.files.internal(filePath));
        Pixmap pixmap100 = new Pixmap(width, height, pixmap200.getFormat());
        pixmap100.drawPixmap(pixmap200, 0, 0, pixmap200.getWidth(), pixmap200.getHeight(), 0, 0, pixmap100.getWidth(), pixmap100.getHeight());
        pixmap200.dispose();
        Texture resizedTexture = new Texture(pixmap100);
        pixmap100.dispose(); // Dispose the pixmap after creating the texture

        return resizedTexture; // Return the newly created texture
    }
    public static int getTextureSize(Texture texture){
        return texture.getWidth();
    }
    public static int getTextureHeight(Texture texture){
        return texture.getHeight();
    }
    public void dispose() { // SpriteBatches and Textures must always be disposed
        walkSheet.dispose();
    }
}
