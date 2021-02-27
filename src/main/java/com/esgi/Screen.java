package com.esgi;

import com.esgi.utils.Keyboard;

abstract class Screen {
    private App parent;
    protected Keyboard inputHandler;

    abstract void init();

    abstract void handleInput();

    public void setParent(App parent) {
        this.parent = parent;
    }

    public void setInputHandler(Keyboard inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void switchGameMode(Screen mode) {
        this.parent.setGameMode(mode);
    }

    public void setPreviousGameMode() {
        this.parent.setPreviousGameMode();
    }
}
