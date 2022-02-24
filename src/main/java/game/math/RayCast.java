package game.math;

import game.GameObject;

import java.util.ArrayList;
import java.util.ListIterator;

import static org.joml.Math.lerp;

public class RayCast extends GameObject {
    protected static RayCast ray;

    protected RayCast(float x, float y){
        super(x, y, 1, 1);
    }

    public static boolean Cast(float x, float y, float angle, float distance){
        ray = new RayCast(x, y);

        float originalX = x;
        float originalY = y;
        float destX = x + (float)Math.cos(angle) * distance;
        float destY = y + (float)Math.sin(angle) * distance;

        for (float t = 0; t < 1; t += 0.01f){
            x = lerp(originalX, destX, t);
            y = lerp(originalY, destY, t);
            for (PositionField object : GameObject.getObjectPositions()){
                if (object.checkIfColliding(ray)){
                    return true;
                }
            }
        }
        return false;
    }
}
