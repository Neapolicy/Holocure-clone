package com.Game.Objects;

import com.Game.Entities.Player;
import com.Game.myGdxGame;

public class Spear extends Weapon{
    public Spear(int damage, Player player, myGdxGame game) {
        super(damage, player, game);
        numSprites = 8;
    }
}
