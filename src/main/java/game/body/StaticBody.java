package game.body;

import game.GameObject;

public class StaticBody extends GameObject {
    protected StaticBody(float x, float y, float height, float width) {
        super(x, y, height, width);
    }

    @Override
    public void update() {
        y -= 0.98;
    }
}
