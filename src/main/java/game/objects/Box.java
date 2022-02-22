package game.objects;

import game.body.RigidBody;

public class Box extends RigidBody {
    public Box(int x, int y, int height, int width, long id) {
        super(x, y, height, width, id);
    }

    @Override
    public void update() {
    }
}
