package com.Game.Entities;

import com.Game.gamestates.Playing;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Rectangle2D;

public class Entity {
    public int speed;
    public Sprite sprite;
    public Vector2 position;
    public Playing screen;
    public Texture texture;
    public Entity(int speed, Texture text, int x, int y, Playing screen){
        this.speed = speed;
        texture = text;
        sprite = new Sprite(texture);
        position = new Vector2(x, y);
        this.screen = screen;
    }
}
