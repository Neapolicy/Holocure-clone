package com.Game.Entities;

import com.Game.myGdxGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Entity extends Sprite {
    public int speed;
    public Sprite sprite;
    public Vector2 position;
    public myGdxGame game;
    public Texture texture;
    public boolean left;
    public int hp, currentHp;
    public Entity(int speed, Texture text, int x, int y, myGdxGame screen){
        this.speed = speed;
        texture = text;
        sprite = new Sprite(texture);
        position = new Vector2(x, y);
        this.game = screen;
    }
    public boolean checkStatus(){
        return currentHp > 0;
    }
    public void takeDamage(int damage){
        currentHp -= damage;
    }
    public void restoreHealth(int health){
        currentHp += health;
    }
    public boolean isLeft(){
        return left;
    }
}
