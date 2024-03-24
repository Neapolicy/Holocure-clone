package com.Game.Entities;

import com.Game.Utils.Animator;
import com.Game.Objects.Weapon;
import com.Game.Utils.Hud;
import com.Game.gamestates.Playing;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Player extends Entity { //https://stackoverflow.com/questions/28000623/libgdx-flip-2d-sprite-animation flip stuff
    private Animator animator = new Animator(this);
    private boolean isRunning = false;
    private boolean isIdle = false;
    private Weapon weapon;

    public Player(int speed, Texture text, int x, int y, myGdxGame screen) {
        super(speed, text, x, y, screen);
        sprite.setSize(50, 50);
        weapon = new Weapon(new Texture("Effects/slash_effect.png"), 100, 100, this, game);
        weapon.sprite.setSize(100, 100);
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
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * deltatime;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * deltatime;
            isMoving = true;
        }

        // Update animation states based on movement
        if (isMoving) {
            playerRun();
            isIdle = false; // Set idle flag to false
        } else {
            playerIdle();
            isRunning = false; // Set running flag to false
        }
        playerBoundaries();
        useWeapon();
    }
    public void useWeapon(){
        weapon.attack(Hud.timePassed, 2);
    }

    public void playerRun() {
        if (!isRunning) {
            animator.changeColnRows(6, 1);
            animator.createAnimation(new Texture("Sprites/player_run.png"), game);
            isRunning = true; // Set the flag to true
        }
    }

    public void playerIdle() {
        if (!isIdle) {
            animator.changeColnRows(5, 1);
            animator.createAnimation(new Texture("Sprites/player_idle.png"), game);
            isIdle = true; // Set the flag to true
        }
    }
    public void playerBoundaries(){
        if (position.x < -50){
            position.x = -50;
        }
        if (position.x > Playing.levelWidth * 16 - sprite.getWidth() * 2){
            position.x = Playing.levelWidth * 16 - sprite.getWidth() * 2;
        }
        if (position.y < 0){
            position.y = 0;
        }
        if (position.y > Gdx.graphics.getHeight() - sprite.getHeight()){
            position.y = Gdx.graphics.getHeight() - sprite.getHeight();
        }
    }
    public void checkLeft(OrthographicCamera camera){ //find cursor location relative to the world location
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        left = touchPos.x < position.x;
    }

    public void draw() { //https://www.youtube.com/watch?v=1fJrhgc0RRw&list=PLZm85UZQLd2SXQzsF-a0-pPF6IWDDdrXt&index=11 watch this
        update(Gdx.graphics.getDeltaTime()); //does movement
        checkLeft(Playing.camera);
        animator.render((int) position.x, (int) position.y); //for some reason it can only draw one thing at a time??
    } //if we stop drawing the player, it will draw the weapon

    public Animator getAnimator() {
        return animator;
    }

    public Weapon getWeapon() {
        return weapon;
    }
    public boolean getLeft(){
        return left;
    }
}
