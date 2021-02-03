package com.esgi;

import com.esgi.utils.Keyboard;

public class App {
    private GameMode mode;
    private Keyboard inputHandler;

    public App(){
        this.inputHandler = new Keyboard();
        this.setGameMode( new MainTitleGameMode() );
        this.run();
    }

    public void setGameMode( GameMode mode ){
        mode.setParent( this );
        mode.setInputHandler( this.inputHandler );
        this.mode = mode;
        this.mode.init();
    }

    public void run(){
        while( true ){
            this.mode.handleInput();
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
