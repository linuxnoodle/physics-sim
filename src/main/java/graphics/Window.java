package graphics;

import game.GameObject;
import game.body.RigidBody;
import game.objects.Box;
import graphics.input.KeyHandler;
import graphics.input.MouseHandler;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.ListIterator;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private int width, height;
    private static long glfwWindow;
    private String title;

    private static Window window = null;

    KeyHandler keyHandler;
    MouseHandler mouseHandler;

    private Window(){
        this.width = 1280;
        this.height = 720;
        this.title = "Physics Simulator";
    }

    public static Window getInstance(){
        if (Window.window == null){
            Window.window = new Window();
        }
        return Window.window;
    }

    public void close() {
        glfwSetWindowShouldClose(glfwWindow, true);
        glfwTerminate();

        System.exit(0);
    }

    public void init(){
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);

        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);

        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window");
        }

        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(1);

        keyHandler = KeyHandler.getInstance();
        mouseHandler = MouseHandler.getInstance();

        glfwSetCursorPosCallback(glfwWindow, mouseHandler::positionCallback);
        glfwSetMouseButtonCallback(glfwWindow, mouseHandler::buttonCallback);
        glfwSetKeyCallback(glfwWindow, keyHandler::callback);

        glfwShowWindow(glfwWindow);

        Box box = new Box(width/2, height/2, 20, 20);

        // used for LWJGL working with GLFW
        GL.createCapabilities();
    }

    public void update(){
        boolean[] keys = keyHandler.getPressedKeys();
        if (keys[GLFW_KEY_ESCAPE]){
            close();
        }

        for (GameObject object: GameObject.getGameObjects()){
            object.update();
        }
    }

    public void render(){
        glfwPollEvents();

        glClearColor(0.3f, 0.3f, 0.3f, 0.3f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // converting from screen coordinates to OpenGL coordinates
        float mouseX = 2.0f * ((float)mouseHandler.getMouseX() + 0.5f) / width - 1.0f;
        float mouseY = -(2.0f * ((float)mouseHandler.getMouseY() + 0.5f) / height - 1.0f);

        // TODO: change from GL_QUADS to GL_TRIANGLES
        glBegin(GL_QUADS);
            // floor rect
            glColor3f(0.15f, 0.15f, 0.15f);

            glVertex2f(-1.0f, -1.0f);
            glVertex2f(1.0f, -1.0f);
            glVertex2f(1.0f, -0.5f);
            glVertex2f(-1.0f, -0.5f);

            // mouse rect
            glColor3f(0.8f, 0.4f, 0.4f);

            glVertex2f(mouseX - 15f / width, mouseY - 15f / height);
            glVertex2f(mouseX + 15f / width, mouseY - 15f / height);
            glVertex2f(mouseX + 15f / width, mouseY + 15f / height);
            glVertex2f(mouseX - 15f / width, mouseY + 15f / height);
        glEnd();

        glBegin(GL_QUADS);
            for (GameObject object : GameObject.getGameObjects()){
                float objectX = 2.0f * (object.getX() + 0.5f) / width - 1.0f;
                float objectY = 2.0f * (object.getY() + 0.5f) / height - 1.0f;

                glColor3f(1.0f, 1.0f, 1.0f);
                glVertex2f(objectX - object.getWidth() / 2, objectY - object.getHeight() / 2);
                glVertex2f(objectX + object.getWidth() / 2, objectY - object.getHeight() / 2);
                glVertex2f(objectX + object.getWidth() / 2, objectY + object.getHeight() / 2);
                glVertex2f(objectX - object.getWidth() / 2, objectY + object.getHeight() / 2);
            }
        glEnd();

        glfwSwapBuffers(glfwWindow);
    }

    public long getWindow(){ return glfwWindow;}
}
