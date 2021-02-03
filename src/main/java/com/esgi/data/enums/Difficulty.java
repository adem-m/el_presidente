package com.esgi.data.enums;

public enum Difficulty {
    EASY(1),
    NORMAL(2),
    HARD(3);

    private int id;

    Difficulty( int id ){
        this.id = id;
    }

    static public Difficulty fromId( int id ){
        switch( id ){
            case 1: return EASY;
            case 2: return NORMAL;
            case 3: return HARD;
        }

        return null;
    }
}
