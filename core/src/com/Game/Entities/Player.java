package com.Game.Entities;

import com.Game.Animation.Animator;
import com.Game.gamestates.Playing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity{
    private Animator animator = new Animator();
    private boolean isRunning = false;
    private boolean isIdle = false;
    public Player(int speed, Texture text, int x, int y, Playing screen) {
        super(speed, text, x, y, screen);
        sprite.setScale(.01f);
        sprite.setSize((float) (.1 * sprite.getWidth()), (float) (.1 * sprite.getHeight()));
        animator.changeColnRows(5, 1);
        animator.createAnimation(new Texture("Sprites/player_idle.png"));
    }
    public void update(float deltatime) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * deltatime;
            playerRun();
            isIdle = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * deltatime;
            playerRun();
            isIdle = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * deltatime;
            playerRun();
            isIdle = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * deltatime;
            playerRun();
            isIdle = false;
        }
        else{
            playerIdle();
            isRunning = false;
        }
    }
    public void playerRun() {
        if (!isRunning) {
            animator.changeColnRows(6, 1);
            animator.createAnimation(new Texture("Sprites/player_run.png"));
            isRunning = true; // Set the flag to true
        }
    }

    public void playerIdle() {
        if (!isIdle) {
            animator.changeColnRows(5, 1);
            animator.createAnimation(new Texture("Sprites/player_idle.png"));
            isIdle = true; // Set the flag to true
        }
    }
    public void draw(){
        update(Gdx.graphics.getDeltaTime());
        animator.render((int) position.x, (int) position.y);
        sprite.setPosition(position.x, position.y);
        sprite.draw(screen.getGame().batch);
    }
    public Animator getAnimator(){
        return animator;
    }
}
