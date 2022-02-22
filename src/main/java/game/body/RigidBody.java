package game.body;

import game.GameObject;

public class RigidBody extends GameObject {
    protected RigidBody(int x, int y, int height, int width, long id) {
        super(x, y, height, width, id);
    }

    protected void update(){
        y += 1;
    }
}
