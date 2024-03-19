package com.Game.Entities;

import com.Game.gamestates.Playing;
import com.Game.myGdxGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Rectangle2D;

public class Entity extends Sprite {
    public int speed;
    public Sprite sprite;
    public Vector2 position;
    public myGdxGame screen;
    public Texture texture;
    public Entity(int speed, Texture text, int x, int y, myGdxGame screen){
        this.speed = speed;
        texture = text;
        sprite = new Sprite(texture);
        position = new Vector2(x, y);
        this.screen = screen;
    }
}
