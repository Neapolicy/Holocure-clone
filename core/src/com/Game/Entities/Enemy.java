package com.Game.Entities;

import com.Game.Utils.Animator;
import com.Game.gamestates.Playing;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Entity{
    public Enemy(int speed, Texture text, int x, int y, myGdxGame screen) {
        super(speed, text, x, y, screen);
        sprite.setSize(50, 50);
    }
    public void followPlayer(float deltatime, Vector2 playerPos) {
        // Update player position (you might want to do this elsewhere)
        playerPos = new Vector2(playerPos.x, playerPos.y);

        // Calculate direction vector
        Vector2 direction = new Vector2(playerPos.x - position.x, playerPos.y - position.y);
        direction.nor(); // Normalize the direction vector

        // Move the enemy (adjust the speed as needed)
         // You can change this value
        position.x += direction.x * speed * deltatime;
        position.y += direction.y * speed * deltatime;
    }
    public void draw(Vector2 playerPos){
        followPlayer(Gdx.graphics.getDeltaTime(), playerPos);
        sprite.setPosition(position.x, position.y);

        sprite.draw(game.batch);
    }
}
