package com.Game.Objects;

import com.Game.Utils.Animator;
import com.Game.Entities.Player;
import com.Game.myGdxGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Weapon {
    public Texture texture;
    public Sprite sprite;
    public Vector2 position;
    public Animator animator = new Animator();
    public myGdxGame game;
    public Weapon(Texture text, int x, int y, myGdxGame game){
        this.game = game;
        texture = text;
        sprite = new Sprite(texture);
        position = new Vector2(x, y);
        animator.createAnimation(text, game);
    }

    public void attack(int time, int cd, Player player){
        if (time % cd == 0){
            updatePosition((int) player.position.x, (int) player.position.y);
            animator.render((int) position.x, (int) position.y);
        }
    }

    public void updatePosition(int x, int y){ //for like melee weapons, range isnt affected
        position.x = x;
        position.y = y;
    }
    public void dispose(){
        texture.dispose();
    }
}