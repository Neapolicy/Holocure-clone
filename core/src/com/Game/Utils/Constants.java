package com.Game.Utils;

import com.badlogic.gdx.physics.box2d.*;

public class Constants {
    public static final float PPM = 32; //alows the binding of 32x32 images to it or smtg idk
    public static final short BIT_WALL = 1;
    public static final short BIT_PLAYER = 2;
    public static final short BIT_ENEMY = 4;
    public static Body createBox(int x, int y, int width, int height, boolean isStatic, World world, short cBits, short mBits, short gIndex){
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

        FixtureDef fdef = new FixtureDef();

        fdef.shape = shape;
        fdef.density = 1;
        fdef.filter.categoryBits = cBits; //is a
        fdef.filter.maskBits = mBits; //collides with
        fdef.filter.groupIndex = gIndex;
        pBody.createFixture(fdef);

        shape.dispose();

        return pBody;
    }
}