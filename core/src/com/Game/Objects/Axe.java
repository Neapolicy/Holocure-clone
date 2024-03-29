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
                x= (int) (player.getPlayerBody().getPosition().x * PPM) - player.getSpriteSheetSize() / player.getNumSprites() - 50;
                y = (int) (player.getPlayerBody().getPosition().y * PPM) - texture.getHeight( )/ numSprites / 2;
                animator.render(x, y);
            } else {
                x= (int) (player.getPlayerBody().getPosition().x * PPM) - player.getSpriteSheetSize() / player.getNumSprites() + 50;
                y = (int) (player.getPlayerBody().getPosition().y * PPM) - texture.getHeight( )/ numSprites / 2;
                animator.render(x, y);
            }
            if (time - lastAttackTime >= cd) { // Check if enough time has passed since the last attack
                if (time % cd > 1 && time % cd < 1.5) {
                    attacking = true;
                    sound.play();
                    lastAttackTime = time; // Update the last attack time
                    weaponBody.setTransform(x / PPM, y / PPM, weaponBody.getAngle());
                    System.out.println(weaponBody.getPosition().y);
                    System.out.println(player.playerBody.getPosition().y);
                }
                attacking = false; // Not enough time has passed since the last attack
            }
        }
    }
}
