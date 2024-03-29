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
    public Body weaponBody;
    private int damage;
    public int x, y;
    public Sound sound;
    public myGdxGame game;

    public Weapon(int damage, Player player, myGdxGame game) {
        this.game = game;
        this.player = player;
        this.damage = damage;
        animator = new Animator(player);
    }
    public void changeTextureSize(String filepath, int width, int height){
        texture = animator.changeTextureSize(filepath, width, height);
        animator.createAnimation(texture, game);
        sprite = new Sprite(texture);
        weaponBody = Constants.createBox(3000, 2000, width / 8, height, false, player.getWorld(),
                Constants.BIT_WEAPON, Constants.BIT_ENEMY, (short) 0);
    }

    public void attack(double time, int cd) {
        if (time % cd > 1 && time % cd < 1.5) {
            if (player.getLeft()) {
                x= (int) (player.getPlayerBody().getPosition().x * PPM) - player.getSpriteSheetSize() / player.getNumSprites() - 50;
                y = (int) (player.getPlayerBody().getPosition().y * PPM) - texture.getHeight( )/ 8;
                animator.render(x, y);
            } else {
                x= (int) (player.getPlayerBody().getPosition().x * PPM) - player.getSpriteSheetSize() / player.getNumSprites() + 100;
                y = (int) (player.getPlayerBody().getPosition().y * PPM) - texture.getHeight() / 8;
                animator.render(x, y);
            }
            if (time - lastAttackTime >= cd) { // Check if enough time has passed since the last attack
                if (time % cd > 1 && time % cd < 1.5) {
                    attacking = true;
                    sound.play();
                    lastAttackTime = time; // Update the last attack time
                    weaponBody.setTransform(x / PPM, y / PPM, weaponBody.getAngle());
                    System.out.println(weaponBody.getPosition().x);
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

