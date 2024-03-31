package com.Game.Objects;

import com.Game.Entities.Player;
import com.Game.myGdxGame;

import static com.Game.Utils.Constants.PPM;

public class Spear extends Weapon{
    public Spear(int damage, Player player, myGdxGame game) {
        super(damage, player, game);
        numSprites = 8;
    }
    public void attack(double time, int cd) {
        if (time % cd > 1 && time % cd < 1.5) {
            if (player.getLeft()) {
                animator.render((int) (weaponBody.getPosition().x * PPM) - 50, (int) (player.getPlayerBody().getPosition().y * PPM) - texture.getHeight() / 2);
                weaponBody.setTransform(player.getPlayerBody().getPosition().x - width / 2 / PPM, player.getPlayerBody().getPosition().y, weaponBody.getAngle());
            } else {
                animator.render((int) (weaponBody.getPosition().x * PPM) - 65, (int) (player.playerBody.getPosition().y * PPM) - texture.getHeight() / 2);
                weaponBody.setTransform(player.getPlayerBody().getPosition().x + width / 2 / PPM, player.getPlayerBody().getPosition().y, weaponBody.getAngle());            }
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
