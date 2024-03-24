package com.Game.Objects;

import com.Game.Entities.Player;
import com.Game.Utils.Animator;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Weapon {
    public Texture texture;
    public Player player;
    public Sprite sprite;
    public Vector2 position;
    public Animator animator;
    public boolean attacking;
    private double lastAttackTime = 0.0;
    private int damage;
    public Sound sound;
    public myGdxGame game;

    public Weapon(Texture text, int x, int y, int damage, Player player, myGdxGame game) {
        this.game = game;
        texture = text;
        this.player = player;
        this.damage = damage;
        animator = new Animator(player);
        sprite = new Sprite(texture);
        position = new Vector2(x, y);
        sound = Gdx.audio.newSound(Gdx.files.internal("Audio/SFX/Gun_Fire.wav"));
        animator.createAnimation(new Texture("Effects/slash_effect.png"), game);
    }

    public void attack(double time, int cd) {
        if (time % cd > 0 && time % cd < 0.5) {
            if (player.getLeft()) {
                animator.render((int) player.position.x, (int) player.position.y);
            } else {
                animator.render((int) (player.position.x + player.sprite.getWidth()), (int) player.position.y);
            }
            if (time - lastAttackTime >= cd) { // Check if enough time has passed since the last attack
                if (time % cd > 0 && time % cd < 0.5) {
                    attacking = true;
                    lastAttackTime = time; // Update the last attack time
                }
                attacking = false; // Not enough time has passed since the last attack
            }
        }
    }

    public void setSize(float width, float height) {
        sprite.setSize(width, height);
    }

    public void dispose() {
        texture.dispose();
    }
}

