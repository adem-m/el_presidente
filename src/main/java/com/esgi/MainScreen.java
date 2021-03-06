package com.esgi;

import com.esgi.utils.ProgressionSaver;

public class MainScreen extends Screen {
    private static final int LOAD = 1;
    private static final int NEW_GAME = 2;
    private static final int EXIT = 3;

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
                    this.loadGame();
                    return;
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

    private void loadGame(){
        if( ProgressionSaver.isFileFound() ){
            this.switchScreen( new PlayScreen( ProgressionSaver.load() ));
        } else {
            System.out.println( "Fichier de sauvegarde non-trouvé ou invalide !" );
            this.init();
        }
    }
}
