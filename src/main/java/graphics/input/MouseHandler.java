package graphics.input;

public class MouseHandler {
    private static MouseHandler handler;
    private double mouseX, mouseY, pmouseX, pmouseY;
    private boolean leftPressed, rightPressed, leftClicked, rightClicked;

    public static MouseHandler getInstance() {
        if (handler == null) {
            handler = new MouseHandler();
        }
        return handler;
    }

    private MouseHandler() {}

    public void positionCallback(long window, double positionX, double positionY) {
        pmouseX = mouseX;
        pmouseY = mouseY;

        mouseX = positionX;
        mouseY = positionY;
    }

    public void buttonCallback(long window, int button, int action, int mods) {
        if (button == 0) {
            if (action == 0) {
                leftPressed = true;
            } else if (action == 1) {
                leftPressed = false;
                leftClicked = true;
            }
        } else if (button == 1) {
            if (action == 0) {
                rightPressed = true;
            } else if (action == 1) {
                rightPressed = false;
                rightClicked = true;
            }
        }
    }

    public double getMouseX() { return mouseX; }
    public double getMouseY() { return mouseY; }
    public double getPMouseX() { return pmouseX; }
    public double getPMouseY() { return pmouseY; }
    public boolean getLeftState() { return leftPressed; }
    public boolean getRightState() { return rightPressed; }
    public boolean getLeftClicked() { return leftClicked; }
    public boolean getRightClicked() { return rightClicked; }
}
