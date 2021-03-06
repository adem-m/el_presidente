package com.esgi;

import com.esgi.modes.State;
import com.esgi.utils.ProgressionSaver;

public class PauseScreen extends Screen {
    private State state;
    private static final int SAVE = 1;
    private static final int RETURN = 2;
    private static final int EXIT = 3;

    public PauseScreen( State state ){
        this.state = state;
    }

    @Override
    void init() {
        System.out.printf( "\n\nQue voulez-vous faire ?\n" );
        System.out.printf( "1 - Sauvegarder\n2 - Retour au jeu\n3 - Retour à l'écran titre\n" );        
    }

    @Override
    void handleInput() {
        int input;
        do {
            input = this.inputHandler.getUserInput();
        } while( input < SAVE || EXIT < input );
        
        switch( input ){
            case SAVE:
                ProgressionSaver.save( this.state );
                this.init();
                return;

            case RETURN:
                this.setPreviousScreen();
                return;

            case EXIT:
                this.switchToMainScreen();
                return;
        }
    }    
}
