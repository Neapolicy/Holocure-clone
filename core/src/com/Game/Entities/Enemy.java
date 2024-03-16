package com.Game.Entities;

import com.Game.gamestates.Playing;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Entity{
    public Enemy(int speed, Texture text, int x, int y, Playing screen) {
        super(speed, text, x, y, screen);
    }
//    public void followPlayer(Vector2 playerpos, Vector2 enemypos){ ill figure this out later lmao
//        Vector2 direction = new Vector2();
//        direction.x = (playerPos.x + 40) - (zombiePos.x + 40);
//        direction.y = (playerPos.y + 40) - (zombiePos.y + 40);
//        direction.nor(); // Normalize the direction vector
//
//// Move the zombie (adjust the speed as needed)
//        float speed = 5; // You can change this value
//        zombie.x += direction.x * speed;
//        zombie.y += direction.y * speed;
//    }
}
