package game.body;

import game.GameObject;
import game.math.RayCast;

public class RigidBody extends GameObject {
    protected RigidBody(float x, float y, float height, float width) {
        super(x, y, height, width);
    }

    @Override
    public void update() {
        super.update();
        if (!RayCast.Cast(x, y, 270, 10)){
            y -= 0.98;
        }
    }
}
