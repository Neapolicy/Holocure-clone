package com.Game.Objects;

import com.Game.Entities.Player;
import com.Game.Utils.Animator;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import static com.Game.Utils.Constants.PPM;

public class Weapon {
    public Texture texture;
    public Player player;
    public Sprite sprite;
    public Vector2 position;
    public Animator animator;
    public boolean attacking;
    public double lastAttackTime = 0.0;
    private int damage;
    public Sound sound;
    public myGdxGame game;

    public Weapon(int x, int y, int damage, Player player, myGdxGame game) {
        this.game = game;
        this.player = player;
        this.damage = damage;
        animator = new Animator(player);
        position = new Vector2(x, y);
    }
    public void changeTextureSize(String filepath, int width, int height){
        texture = animator.changeTextureSize(filepath, width, height);
        animator.createAnimation(texture, game);
        sprite = new Sprite(texture);
    }

    public void attack(double time, int cd) {
        if (time % cd > 1 && time % cd < 1.5) {
            if (player.getLeft()) {
                animator.render((int) (player.getPlayerBody().getPosition().x * PPM) - player.getSpriteSheetSize() / player.getNumSprites() - 100,
                        (int) (player.getPlayerBody().getPosition().y * PPM) - player.getSpriteHeight()/player.getNumSprites());
            } else {
                animator.render((int) (player.getPlayerBody().getPosition().x * PPM) - player.getSpriteSheetSize() / player.getNumSprites() + 100,
                        (int) (player.getPlayerBody().getPosition().y * PPM) - player.getSpriteHeight()/player.getNumSprites());
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

