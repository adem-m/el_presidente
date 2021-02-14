package com.esgi.data.enums;

public enum Difficulty {
    EASY(1, 1),
    NORMAL(2, 2),
    HARD(3, 4);

    private int id;
    private int multiplier;

    Difficulty( int id, int multiplier ){
        this.id = id;
        this.multiplier = multiplier;
    }

    public int getMultiplier(){
        return this.multiplier;
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
