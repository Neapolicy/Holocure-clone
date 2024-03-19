package com.Game.Entities;

import com.Game.Animation.Animator;
import com.Game.Objects.Weapon;
import com.Game.gamestates.Playing;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Entity {
    private Animator animator = new Animator();
    private boolean isRunning = false;
    private boolean isIdle = false;
    private boolean left = false;
    private Weapon weapon;

    public Player(int speed, Texture text, int x, int y, myGdxGame screen) {
        super(speed, text, x, y, screen);
        sprite.setScale(.01f);
        weapon = new Weapon(new Texture("Effects/slash_effect.png"), 1000, 1000, screen);
    }

    public void update(float deltatime) {
        // Check for movement keys
        boolean isMoving = false; // Flag to track movement

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * deltatime;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * deltatime;
            isMoving = true;
            left = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * deltatime;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * deltatime;
            isMoving = true;
            left = false;
        }

        // Update animation states based on movement
        if (isMoving) {
            playerRun();
            isIdle = false; // Set idle flag to false
        } else {
            playerIdle();
            isRunning = false; // Set running flag to false
        }
    }

    public void playerRun() {
        if (!isRunning) {
            animator.changeColnRows(6, 1);
            if (!left) animator.createAnimation(new Texture("Sprites/player_run.png"), screen);
            else {
                animator.createAnimation(new Texture("Sprites/player_run_left.png"), screen);
            }
            isRunning = true; // Set the flag to true
        }
    }

    public void playerIdle() {
        if (!isIdle) {
            animator.changeColnRows(5, 1);
            if (!left) animator.createAnimation(new Texture("Sprites/player_idle.png"), screen);
            else {
                animator.createAnimation(new Texture("Sprites/player_idle_left.png"), screen);
            }
            isIdle = true; // Set the flag to true
        }
    }

    public void draw() { //https://www.youtube.com/watch?v=1fJrhgc0RRw&list=PLZm85UZQLd2SXQzsF-a0-pPF6IWDDdrXt&index=11 watch this
        update(Gdx.graphics.getDeltaTime()); //does movement
        animator.render((int) position.x, (int) position.y);
    }

    public Animator getAnimator() {
        return animator;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
