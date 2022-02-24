package game.math;

import game.GameObject;

public class PositionField {
    public float x, y, height, width;
    public PositionField(float x, float y, float height, float width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public boolean checkIfColliding(GameObject object){
        return !(this.x + this.width < object.getX() || this.x > object.getX() + object.getWidth() || this.y + this.height < object.getY() || this.y > object.getY() + object.getHeight());
    }
}
