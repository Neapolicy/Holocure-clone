package com.Game.Utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class TiledObjectUtil {
    public static void parseObjectLayer(World world, MapObjects mapObjects) {
        for (MapObject object : mapObjects) { //for some reason java doesn't go through this list at all
            if (object instanceof PolygonMapObject) { //PolygonMapObject instead of polyline because libgdx updated it, also it's getPolygon instead of polyLine
                createStaticBody((PolygonMapObject) object, world); //also idk why is object not being recognized as polygonMapObject
            }
            if (object instanceof RectangleMapObject){
                createRectangle((RectangleMapObject) object);
                createStaticBody((RectangleMapObject) object, world);
            }
        }
    }
    public static void createStaticBody(PolygonMapObject polygonMapObject, World world){
        Body body;
        Shape shape = createPolygon(polygonMapObject);
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bDef);
        body.createFixture(shape, 1f);
        shape.dispose();
    }
    public static void createStaticBody(RectangleMapObject rectangleMapObject, World world){
        Body body;
        Shape shape = createRectangle(rectangleMapObject);
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bDef);
        body.createFixture(shape, 1f);
        shape.dispose();
    }
    private static Shape createPolygon(PolygonMapObject polygon){
        float[] vertices = polygon.getPolygon().getTransformedVertices(); //transformed vertices for unprojected location, better coords to work with
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < worldVertices.length; i++){
            worldVertices[i] = new Vector2(vertices[i * 2] / Constants.PPM, vertices[i * 2] + 1 / Constants.PPM); //you only want every other vertice, because 1 line, has two vertices, so a v has four vertices instead of 3
        }
        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }
    private static Shape createRectangle(RectangleMapObject rectangleMapObject){
        Rectangle rectangle = rectangleMapObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / Constants.PPM, (rectangle.y + rectangle.height * 0.5f ) / Constants.PPM);
        polygon.setAsBox(rectangle.width * 0.5f /Constants.PPM, rectangle.height * 0.5f / Constants.PPM, size, 0.0f);
        return polygon;
    }
}