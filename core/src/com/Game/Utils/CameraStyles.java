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

    public static void lerpToTarget(Camera camera, Vector2 target) {
        // a + (b - a) * lerp factor
        Vector3 position = camera.position;
        position.x = camera.position.x + (target.x - camera.position.x) * .1f;
        position.y = camera.position.y + (target.y - camera.position.y) * .1f;
        camera.position.set(position);
        camera.update();
    }

    public static void lockAverageBetweenTargets(Camera camera, Vector2 targetA, Vector2 targetB) {
        Vector3 position = camera.position;
        position.x = (targetA.x + targetB.x) / 2;
        position.y = (targetA.y + targetB.y) / 2;
        camera.position.set(position);
        camera.update();
    }

    public static void lerpAverageBetweenTargets(Camera camera, Vector2 targetA, Vector2 targetB) {
        float avgX = (targetA.x + targetB.x) / 2;
        float avgY = (targetA.y + targetB.y) / 2;

        Vector3 position = camera.position;
        position.x = camera.position.x + (avgX - camera.position.x) * .1f;
        position.y = camera.position.y + (avgY - camera.position.y) * .1f;
        camera.position.set(position);
        camera.update();
    }
}
