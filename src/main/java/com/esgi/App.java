package com.esgi;

import java.util.Stack;
import com.esgi.utils.Keyboard;

public class App {
    private Screen screen;
    private final Keyboard inputHandler;
    private final Stack<Screen> screensStack;

    public App() {
        this.screensStack = new Stack<>();
        this.inputHandler = new Keyboard();
        this.setScreen(new MainTitleScreen());
        this.run();
    }

    public void setScreen( Screen screen ){
        this.screensStack.push(screen);
        screen.setParent(this);
        screen.setInputHandler(this.inputHandler);
        this.screen = screen;
        this.screen.init();
    }

    public void setPreviousScreen() {
        if (!this.screensStack.isEmpty()) {
            this.screensStack.pop();
        }

        if (this.screensStack.isEmpty()) {
            System.out.println("Erreur de la stack de menus...");
            System.exit(1);
        }

        this.setScreen(this.screensStack.pop());
    }

    public void reinitializeScreens( Screen screen ){
        this.screensStack.clear();
        this.setScreen( screen );
    } 

    public void run() {
        while (true) {
            this.screen.handleInput();
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
