package com.Game.Objects;

import com.Game.Entities.Entity;
import com.Game.Entities.Player;
import com.Game.myGdxGame;
import com.badlogic.gdx.graphics.Texture;

public class Axe extends Weapon {
    public Axe(int x, int y, int damage, Player player, myGdxGame game) {
        super(x, y, damage, player, game);
    }

    public void attack(double time, int cd) {
        if (time % cd > 1 && time % cd < 1.5) {
            if (player.getLeft()) {
                animator.render((int) (player.getCenterX() - sprite.getRegionWidth() / 8), (int) (player.getCenterY() - sprite.getHeight() / 2));
            } else {
                animator.render((int) (player.getCenterX() + sprite.getRegionWidth() / 8), (int) (player.getCenterY() - sprite.getHeight() / 2));
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
}
