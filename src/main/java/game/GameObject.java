package game;

public abstract class GameObject {
    protected float x, y, height, width;
    protected boolean visible;
    protected long id;

    public GameObject(int x, int y, int height, int width, long id) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.id = id;
        this.visible = true;
    }

    public boolean checkIfColliding(GameObject object){
        return !(this.x + this.width < object.x || this.x > object.x + object.width || this.y + this.height < object.y || this.y > object.y + object.height);
    }

    public long getID() { return id; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getHeight() { return height; }
    public float getWidth() { return width; }
    public boolean isVisible() { return visible; }
}
