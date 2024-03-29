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
    public void attack(double time, int cd) {
        if (time % cd > 1 && time % cd < 1.5) {
            if (player.getLeft()) {
                animator.render((int) (weaponBody.getPosition().x * PPM) - (50 / 2), (int) (weaponBody.getPosition().y * PPM) - (player.getTexture().getHeight() / 2) - texture.getHeight() / 2);
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
}
