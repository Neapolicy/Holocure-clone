package com.Game.Entities;

import com.Game.Animation.Animator;
import com.Game.gamestates.Playing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity{
    private Animator animator = new Animator();
    private boolean moving;
    public Player(int speed, Texture text, int x, int y, Playing screen) {
        super(speed, text, x, y, screen);
        sprite.setScale(.01f);
        sprite.setSize((float) (.1 * sprite.getWidth()), (float) (.1 * sprite.getHeight()));
        animator.changeColnRows(5, 1);
        animator.createAnimation("Sprites/player_idle.png");
    }
    public void update(float deltatime) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * deltatime;
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * deltatime;
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * deltatime;
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * deltatime;
            moving = true;
        }
        else{
            moving = false;
        }
        if (moving){
            playerRun();
        }
        else{
            playerIdle();
        }
    }
    public void playerRun(){
        animator.createAnimation("Sprites/player_run.png");
        animator.changeColnRows(6, 1);
    }
    public void playerIdle(){
        animator.createAnimation("Sprites/player_idle.png");
        animator.changeColnRows(5, 1);
    }
    public void draw(){
        update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(screen.getGame().batch);
        animator.render((int) position.x, (int) position.y);
    }
    public Animator getAnimator(){
        return animator;
    }
}
