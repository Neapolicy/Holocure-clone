package com.Game.Objects;

import com.Game.Utils.Animator;
import com.Game.Entities.Player;
import com.Game.gamestates.Playing;
import com.Game.myGdxGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.text.DecimalFormat;

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

    public void attack(float time, int cd, Player player){
        if (time % cd > 0 && time % cd < .5){
            if (player.getLeft()){
                updatePosition((int) player.position.x, (int) player.position.y);
                animator.render((int) position.x - 25, (int) position.y);
            }
            else{
                updatePosition((int) player.position.x, (int) player.position.y);
                animator.render((int) position.x + 50, (int) position.y);
            }
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
