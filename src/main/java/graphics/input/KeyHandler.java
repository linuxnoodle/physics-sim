package graphics.input;

import org.lwjgl.glfw.GLFW;

public class KeyHandler {
    private static KeyHandler handler;
    private boolean[] pressedKeys = new boolean[350];

    private KeyHandler(){}

    public static KeyHandler getInstance(){
        if (handler == null){
            handler = new KeyHandler();
        }
        return handler;
    }

    public void callback(long window, int key, int scancode, int action, int mods){
        if (action == GLFW.GLFW_PRESS) {
            handler.pressedKeys[key] = true;
        } else if (action == GLFW.GLFW_RELEASE) {
            handler.pressedKeys[key] = false;
        }
    }

    public boolean[] getPressedKeys(){ return pressedKeys; }
}
