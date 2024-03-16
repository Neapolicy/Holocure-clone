package com.Game.Entities;

import com.Game.Animation.Animator;
import com.Game.gamestates.Playing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity{
    private Animator animator = new Animator();
    public Player(int speed, Texture text, int x, int y, Playing screen) {
        super(speed, text, x, y, screen);
        animator.createAnimation("Sprites/sprite_sheet_walk.png");
    }
    public void update(float deltatime) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * deltatime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * deltatime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * deltatime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * deltatime;
        }
    }

    public void playAnimation(){
        animator.render((int) position.x, (int) position.y);
        animator.dispose();
    }
    public void draw(){
        update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(screen.getGame().batch);
    }
}
