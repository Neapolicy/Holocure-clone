package com.Game.Entities;

import com.Game.gamestates.Playing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Entity{
    private Vector2 playerPos;
    public Enemy(int speed, Texture text, int x, int y, Vector2 playerPos, Playing screen) {
        super(speed, text, x, y, screen);
        sprite.setScale(.1f);
        this.playerPos = playerPos;
    }
    public void followPlayer(float deltatime) {
        // Calculate direction vector
        Vector2 direction = new Vector2(playerPos.x - position.x, playerPos.y - position.y);
        direction.nor(); // Normalize the direction vector

        // Move the enemy (adjust the speed as needed)
        float speed = 400; // You can change this value
        position.x += direction.x * speed * deltatime;
        position.y += direction.y * speed * deltatime;
    }
    public void draw(){
        followPlayer(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(screen.getGame().batch);
    }
}
