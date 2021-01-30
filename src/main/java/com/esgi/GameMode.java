package com.esgi;

abstract class GameMode {
    private App parent;

    abstract void init();
    abstract void handleInput();

    public void setParent( App parent ){
        this.parent = parent;
    }

    public void switchGameMode( GameMode mode ){
        this.parent.setGameMode( mode );
    }
}
