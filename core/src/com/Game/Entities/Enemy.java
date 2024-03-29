package com.Game.Entities;

import com.Game.Utils.Constants;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static com.Game.Utils.Constants.PPM;

public class Enemy extends Entity{
    private Body enemyBody;
    public Enemy(int speed, Texture text, int x, int y, myGdxGame screen, World world) {
        super(speed, text, x, y, screen);
        sprite.setSize(50, 50);
        enemyBody = Constants.createBox(x, y, 32, 32, false, world);
    }
    public void followPlayer(float deltatime, Vector2 playerPos) {
        // Update player position (you might want to do this elsewhere)
        playerPos = new Vector2(playerPos.x * PPM, playerPos.y * PPM);

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
