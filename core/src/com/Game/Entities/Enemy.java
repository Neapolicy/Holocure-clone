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
        sprite.setSize(10, 10);
        enemyBody = Constants.createBox(x, y, 32, 32, false, world,
                Constants.BIT_ENEMY, Constants.BIT_WALL, (short) 0);
    }
    public void followPlayer(Vector2 playerPos) {
        // Calculate direction vector
        Vector2 direction = new Vector2(playerPos.x - enemyBody.getPosition().x,
                playerPos.y - enemyBody.getPosition().y);
        direction.nor(); // Normalize the direction vector

        // Move the enemy (adjust the speed as needed)
        enemyBody.setLinearVelocity(direction.x * speed, direction.y * speed);
    }
    public void draw(Vector2 playerPos){
        followPlayer(playerPos);
        sprite.setPosition(enemyBody.getPosition().x * PPM, enemyBody.getPosition().y * PPM);

        sprite.draw(game.batch);
    }
}
