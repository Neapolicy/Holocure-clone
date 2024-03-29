package com.Game.Entities;

import com.Game.Objects.Axe;
import com.Game.Objects.Spear;
import com.Game.Objects.Weapon;
import com.Game.Utils.Animator;
import com.Game.Utils.Constants;
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

import static com.Game.Utils.Constants.PPM;

public class Player extends Entity { //https://stackoverflow.com/questions/28000623/libgdx-flip-2d-sprite-animation flip stuff
    public static String weaponChoice;
    private Animator animator = new Animator(this);
    public Body playerBody;
    private World world;
    private int numSprites, spriteSheetSize, spriteHeight;
    private boolean isRunning = false;
    private boolean isIdle = false;
    private Weapon weapon;
    private Texture playerRun, playerIdle;

    public Player(int speed, Texture text, int x, int y, myGdxGame screen, World world) {
        super(speed, text, x, y, screen);
        this.world = world;
        currentHp = 100;
        hp = 100;
        initWeapon();

        playerRun = animator.changeTextureSize("Sprites/player_run.png", 384, 64);
        playerIdle = animator.changeTextureSize("Sprites/player_idle.png", 320, 64);
        playerBody = Constants.createBox(x, y, 32, 32, false, world,
                Constants.BIT_PLAYER, Constants.BIT_WALL, (short) 0);
    }
    public void initWeapon(){
        if (weaponChoice.equals("spear")){
            weapon = new Spear( 10, this, game);
            weapon.changeColumnsNRows(8, 1); //then put a bunch of if statements here once i implement weapon selection
            weapon.changeTextureSize("Effects/spear_pierce.png", 1000, 100);
            weapon.setAudio("Audio/SFX/spear_swing.wav");
        }
        if (weaponChoice.equals("sword")){
            weapon = new Axe( 10, this, game);
            weapon.changeTextureSize("Effects/slash_effect.png", 500, 400);
            weapon.setAudio("Audio/SFX/ax_swing.wav");
        }
        if (weaponChoice.equals("gun")){
            weapon.changeTextureSize("Effects/slash_effect.png", 500, 600);
            weapon.setAudio("Audio/SFX/sword_swing.wav");
        }
    }
    public void update() {
        // Check for movement keys
        boolean isMoving = false; // Flag to track movement

        int horizontalForce = 0; //sets to zero every time, so if player doesn't move, h Force set to zero
        int verticalForce = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            horizontalForce +=1;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            horizontalForce -=1;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            verticalForce +=1;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            verticalForce -=1;
            isMoving = true;
        }
        playerBody.setLinearVelocity(horizontalForce * 5, verticalForce * 5); //move w/ velocity of 5mps

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
            numSprites = 6;
            spriteSheetSize = Animator.getTextureWidth(playerRun);
            spriteHeight = Animator.getTextureHeight(playerRun);
            animator.changeColnRows(6, 1);
            animator.createAnimation(playerRun, game);
            isRunning = true; // Set the flag to true
        }
    }

    public void playerIdle() {
        if (!isIdle) {
            numSprites = 5;
            spriteSheetSize = Animator.getTextureWidth(playerIdle);
            spriteHeight = Animator.getTextureHeight(playerIdle);
            animator.changeColnRows(5, 1);
            animator.createAnimation(playerIdle, game);
            isIdle = true; // Set the flag to true
        }
    }
    public void checkLeft(OrthographicCamera camera){ //find cursor location relative to the world location
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        left = touchPos.x < playerBody.getPosition().x * PPM;
    }
    public void draw() { //https://www.youtube.com/watch?v=1fJrhgc0RRw&list=PLZm85UZQLd2SXQzsF-a0-pPF6IWDDdrXt&index=11 watch this
        update(); //does movement
        checkLeft(Playing.camera);
        animator.render((int) (playerBody.getPosition().x * PPM) - ((spriteSheetSize / numSprites / 2)),
                (int) (playerBody.getPosition().y * PPM) - ((spriteHeight / numSprites)));
    }

    public Animator getAnimator() {
        return animator;
    }

    public Weapon getWeapon() {
        return weapon;
    }
    public boolean getLeft(){
        return left;
    }
    public Body getPlayerBody(){return playerBody;}
    public int getNumSprites(){return numSprites;}
    public int getSpriteSheetSize(){return spriteSheetSize;}

    public int getSpriteHeight() {return spriteHeight;}
    public World getWorld(){return world;}
}
