package game;

import game.math.PositionField;

import java.util.ArrayList;

public class GameObject {
    private static ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static ArrayList<PositionField> objectPositions = new ArrayList<>();

    protected float x, y, height, width;
    protected boolean visible;

    protected int currentObjectCount = 0;
    protected int id;

    protected GameObject(float x, float y, float height, float width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.visible = true;
        this.id = currentObjectCount++;

        gameObjects.add(this);
        objectPositions.add(new PositionField(x, y, height, width));
    }

    // TODO: Implement id changing when object is removed
    public void update() {
        objectPositions.set(id, new PositionField(x, y, height, width));
    }

    public long getID() { return id; }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getHeight() { return height; }
    public float getWidth() { return width; }

    public static ArrayList<GameObject> getGameObjects() { return gameObjects; }
    // Created because of concurrent modification exception when iterating through the gameObjects array for both updating and positioning
    public static ArrayList<PositionField> getObjectPositions() { return objectPositions; }

    public boolean getVisibility() { return visible; }
}
