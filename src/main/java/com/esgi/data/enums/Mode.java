package com.esgi.data.enums;

public enum Mode {
    SANDBOX( 1 ),
    SCENARIO( 2 );

    private final int id;

    Mode( int id ){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    static public Mode fromId( int id ) throws Error {
        switch( id ){
            case 1:
                return SANDBOX;
            case 2:
                return SCENARIO;
            default:
                throw new Error( "Identifiant de mode invalide" );
        }
    }
}
