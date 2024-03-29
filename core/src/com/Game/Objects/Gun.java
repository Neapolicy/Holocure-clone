package com.Game.Objects;

import com.Game.Entities.Player;
import com.Game.myGdxGame;

public class Gun extends Weapon{
    public Gun(int x, int y, int damage, Player player, myGdxGame game) {
        super(damage, player, game);
        numSprites = 1;
    }
}
