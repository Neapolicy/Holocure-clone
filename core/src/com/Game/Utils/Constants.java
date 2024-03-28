package com.Game.Utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Constants {
    public static final float PPM = 32; //alows the binding of 32x32 images to it or smtg idk
    public static Body createBox(int x, int y, int width, int height, boolean isStatic, World world){
        Body pBody;
        BodyDef def = new BodyDef();
        if (isStatic){
            def.type = BodyDef.BodyType.StaticBody;
        }
        else{
            def.type = BodyDef.BodyType.DynamicBody; //dynamic body allows it to move and interct with the world
        }
        def.position.set(x / PPM, y / PPM); //reason why the player box is at bottom left!!
        def.fixedRotation = true; //if false, player body will rotate
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM); //takes it from the center, so width and height is actually 64x64, not 32x32

        pBody.createFixture(shape, 1); //give density of 1 by default, unless you want more density
        shape.dispose();

        return pBody;
    }
}