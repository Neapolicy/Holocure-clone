package com.Game.Entities;

import com.Game.Objects.Axe;
import com.Game.Objects.Spear;
import com.Game.Objects.Weapon;
import com.Game.Utils.Animator;
import com.Game.Utils.Hud;
import com.Game.gamestates.Playing;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Entity { //https://stackoverflow.com/questions/28000623/libgdx-flip-2d-sprite-animation flip stuff
    public static String weaponChoice;
    private Animator animator = new Animator(this);
    private World world;
    public Body b2Body;
    private boolean isRunning = false;
    private boolean isIdle = false;
    private Weapon weapon;
    private Texture playerRun, playerIdle;

    public Player(int speed, Texture text, int x, int y, myGdxGame screen) {
        super(speed, text, x, y, screen);
//        makePlayer();
        currentHp = 100;
        hp = 100;
        initWeapon();

        playerRun = animator.changeTextureSize("Sprites/player_run.png", 384, 64);
        playerIdle = animator.changeTextureSize("Sprites/player_idle.png", 320, 64);
    }
    public void initWeapon(){
        if (weaponChoice.equals("spear")){
            weapon = new Spear(100, 100, 10, this, game);
            weapon.changeColumnsNRows(8, 1); //then put a bunch of if statements here once i implement weapon selection
            weapon.changeTextureSize("Effects/spear_pierce.png", 1000, 100);
            weapon.setAudio("Audio/SFX/spear_swing.wav");
        }
        if (weaponChoice.equals("sword")){
            weapon = new Axe(100, 100, 10, this, game);
            weapon.changeTextureSize("Effects/slash_effect.png", 500, 600);
            weapon.setAudio("Audio/SFX/ax_swing.wav");
        }
        if (weaponChoice.equals("gun")){
            weapon.changeTextureSize("Effects/slash_effect.png", 500, 600);
            weapon.setAudio("Audio/SFX/sword_swing.wav");
        }
    }
    public void makePlayer(){
//        BodyDef bdef = new BodyDef();
//        bdef.position.set(position.x, position.y);
//        bdef.type = BodyDef.BodyType.DynamicBody;
//        b2Body = world.createBody(bdef);
//
//        FixtureDef fDef = new FixtureDef();
//        CircleShape shape = new CircleShape();
//        shape.setRadius(5 / myGdxGame.PPM);
//
//        fDef.shape = shape;
//        b2Body.createFixture(fDef);
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
        useWeapon();
    }
    public void useWeapon(){
        weapon.attack(Hud.timePassed, 2);
    }

    public void playerRun() {
        if (!isRunning) {
            animator.changeColnRows(6, 1);
            animator.createAnimation(playerRun, game);
            isRunning = true; // Set the flag to true
        }
    }

    public void playerIdle() {
        if (!isIdle) {
            animator.changeColnRows(5, 1);
            animator.createAnimation(playerIdle, game);
            isIdle = true; // Set the flag to true
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
