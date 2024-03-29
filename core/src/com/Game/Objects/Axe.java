package com.Game.Objects;

import com.Game.Entities.Entity;
import com.Game.Entities.Player;
import com.Game.myGdxGame;
import com.badlogic.gdx.graphics.Texture;

import static com.Game.Utils.Constants.PPM;

public class Axe extends Weapon {
    public Axe(int damage, Player player, myGdxGame game) {
        super(damage, player, game);
        numSprites = 6;
    }
    public void attack(double time, int cd) { //y axis is weird
        if (time % cd > 1 && time % cd < 1.5) {
            if (player.getLeft()) {
                animator.render((int) (player.getPlayerBody().getPosition().x * PPM) - player.getSpriteSheetSize() / player.getNumSprites() - 100,
                        (int) (player.getPlayerBody().getPosition().y * PPM / 2));
            } else {
                animator.render((int) (player.getPlayerBody().getPosition().x * PPM) - player.getSpriteSheetSize() / player.getNumSprites() + 100,
                        (int) (player.getPlayerBody().getPosition().y * PPM));
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
