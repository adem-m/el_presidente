package com.esgi;

import java.util.Stack;

import com.esgi.utils.Keyboard;

public class App {
    private Screen mode;
    private final Keyboard inputHandler;
    protected Stack<Screen> modesStack;

    public App() {
        this.modesStack = new Stack<>();
        this.inputHandler = new Keyboard();
        this.setGameMode(new MainTitleScreen());
        this.run();
    }

    public void setGameMode(Screen mode) {
        this.modesStack.push(mode);
        mode.setParent(this);
        mode.setInputHandler(this.inputHandler);
        this.mode = mode;
        this.mode.init();
    }

    public void setPreviousGameMode() {
        if (!this.modesStack.isEmpty()) {
            this.modesStack.pop();
        }

        if (this.modesStack.isEmpty()) {
            System.out.println("Erreur de la stack de menus...");
            System.exit(1);
        }

        this.setGameMode(this.modesStack.pop());
    }

    public void run() {
        while (true) {
            this.mode.handleInput();
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
