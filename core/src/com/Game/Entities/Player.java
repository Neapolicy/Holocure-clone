package com.Game.Entities;

import com.Game.gamestates.Playing;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity{
    public Player(int speed, Texture text, int x, int y, Playing screen) {
        super(speed, text, x, y, screen);
    }
}
