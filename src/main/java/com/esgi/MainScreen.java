package com.esgi;

public class MainScreen extends Screen {
    private final int LOAD = 1;
    private final int NEW_GAME = 2;
    private final int EXIT = 3;

    @Override
    void init(){
        System.out.printf( "\n\n%d - %s\n", LOAD, "Charger une partie" );
        System.out.printf( "%d - %s\n", NEW_GAME, "Nouvelle partie" );
        System.out.printf( "%d - %s\n", EXIT, "Fermer l'application" );
    }

    @Override
    void handleInput(){
        int input;
        do {
            input = this.inputHandler.getUserInput();
            switch( input ){
                case LOAD :
                    System.out.println( "Go to load screen here !" );
                    System.exit( 0 );
                    break;
                case NEW_GAME :
                    this.switchScreen( new ModeScreen() );
                    return;

                case EXIT :
                    System.out.println( "Fermeture..." );
                    System.exit( 0 );
                    break;
                default:
                    break;
            }
        } while( input < LOAD || EXIT < input );
    }    
}
