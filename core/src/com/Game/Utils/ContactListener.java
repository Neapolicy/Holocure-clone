package com.Game.Utils;

import com.Game.Entities.Enemy;
import com.Game.Entities.Player;
import com.badlogic.gdx.physics.box2d.*;
public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa == null || fb == null) {
            return;
        }
//        if (fa.getUserData() == null || fb.getUserData() == null) {
//            return;
//        }

        if(isEnemyContact(fa, fb)){
            System.out.println("asd");
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
    public boolean isEnemyContact(Fixture a, Fixture b){
        return (a.getUserData() instanceof Enemy && b.getUserData() instanceof Player);
    }
}
