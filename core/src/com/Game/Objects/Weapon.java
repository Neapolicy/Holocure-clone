package com.Game.Objects;

import com.Game.Entities.Player;
import com.Game.Utils.Animator;
import com.Game.myGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Weapon {
    public Texture texture;
    public Sprite sprite;
    public Vector2 position;
    public Animator animator = new Animator();
    public Sound sound;
    public myGdxGame game;
    public Weapon(Texture text, int x, int y, String fileLink, myGdxGame game){
        this.game = game;
        texture = text;
        sprite = new Sprite(texture);
        position = new Vector2(x, y);
        sound = Gdx.audio.newSound(Gdx.files.internal(fileLink));
    }

    public void attack(float time, int cd, Player player){
        animator.createAnimation(texture, game);
        if (time % cd > 0 && time % cd < .5){
            if (player.getLeft()){
                animator.render((int) player.position.x - 25, (int) player.position.y);
            }
            else{
                animator.render((int) player.position.x + 50, (int) player.position.y);
            }
        }
    }
    public void dispose(){
        texture.dispose();
    }
}
