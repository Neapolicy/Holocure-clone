package com.Game.Objects;

import com.Game.Entities.Player;
import com.Game.Utils.Animator;
import com.Game.Utils.Constants;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static com.Game.Utils.Constants.PPM;

public class Weapon {
    public Texture texture;
    public Player player;
    public Sprite sprite;
    public Animator animator;
    public boolean attacking;
    public double lastAttackTime = 0.0;
    public int numSprites;
    public Body weaponBody;
    private int damage;
    public int width;
    public Sound sound;
    public myGdxGame game;

    public Weapon(int damage, Player player, myGdxGame game) {
        this.game = game;
        this.player = player;
        this.damage = damage;
        animator = new Animator(player);
    }
    public void changeTextureSize(String filepath, int width, int height, int bodWidth, int bodHeight){
        texture = animator.changeTextureSize(filepath, width, height);
        animator.createAnimation(texture, game);
        sprite = new Sprite(texture);
        weaponBody = Constants.createBox(3000, 2000, bodWidth, bodHeight, false, player.getWorld(),
                Constants.BIT_WEAPON, Constants.BIT_ENEMY, (short) 0);
        this.width = width / numSprites;
    }

    public void attack(double time, int cd) {
        if (time % cd > 1 && time % cd < 1.5) {
            if (player.getLeft()) {
                animator.render((int) (weaponBody.getPosition().x * PPM) - (50 / 2), (int) (weaponBody.getPosition().y * PPM) - (player.getTexture().getHeight() / 2));
                weaponBody.setTransform(player.getPlayerBody().getPosition().x - width / 2 / PPM, player.getPlayerBody().getPosition().y, weaponBody.getAngle());
            } else {
                animator.render((int) (weaponBody.getPosition().x * PPM) - 25, (int) (player.playerBody.getPosition().y * PPM) - texture.getHeight() / 2);
                weaponBody.setTransform(player.getPlayerBody().getPosition().x + width / 2 / PPM, player.getPlayerBody().getPosition().y, weaponBody.getAngle());
            }
            if (time - lastAttackTime >= cd) { // Check if enough time has passed since the last attack
                if (time % cd > 1 && time % cd < 1.5) {
                    attacking = true;
                    sound.play();
                    lastAttackTime = time; // Update the last attack time
                }
                attacking = false; // Not enough time has passed since the last attack
            }
        }
    }
    public void setAudio(String fileLink){
        sound = Gdx.audio.newSound(Gdx.files.internal(fileLink));
    }
    public void changeColumnsNRows(int columns, int rows){
        animator.changeColnRows(columns, rows);
    }

    public void dispose() {
        texture.dispose();
        sound.dispose();
        animator.dispose();
        game.dispose();
    }
}

