import graphics.Window;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Main {
    public static void main(String[] args) {
        Window window = Window.getInstance();

        window.init();
        while (!glfwWindowShouldClose(window.getWindow())) {
            window.update();
            window.render();
        }
        window.close();
    }
}
