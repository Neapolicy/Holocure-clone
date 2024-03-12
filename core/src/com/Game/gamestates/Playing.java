package com.Game.gamestates;

import com.Game.myGdxGame;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Playing {
    private Music bgm;
    public Playing(){
        myGdxGame.img.add(new Texture("Sprites/bullet.png"));
    }
}
