package com.Game.Utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraStyles { //https://github.com/cnnranderson/YT_Tut1/blob/master/core/src/com/cnnranderson/tutorial/states/DungeonState.java

    public static void lockOnTarget(Camera camera, Vector2 target) { //massive shoutout to connor anderson the GOAT GOAT
        Vector3 position = camera.position;
        position.x = target.x;
        position.y = target.y;
        camera.position.set(position);
        camera.update();
    }
    public static void boundary(Camera camera, float startX, float startY, float width, float height){
        Vector3 position = camera.position;
        if (position.x < startX) {
            position.x = startX;
        }
        if (position.y < startY){
            position.y = startY;
        }
        if (position.x > startX + width){
            position.x = startX + width;
        }
        if (position.y > startY + height){
            position.y = startY + height;
        }
        camera.position.set(position);
        camera.update();
    }
}
