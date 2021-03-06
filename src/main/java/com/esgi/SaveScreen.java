package com.esgi;

import com.esgi.modes.State;
import com.esgi.utils.ProgressionSaver;

public class SaveScreen extends Screen {
    private State state;
    private final int NO = 2;
    private final int YES = 1;

    @Override
    void init() {
        System.out.printf( "\n\nSauvegarder ?" );
        System.out.printf( "1 - Oui\n2 - Non\n" );        
    }

    @Override
    void handleInput() {
        int input;
        do {
            input = this.inputHandler.getUserInput();
        } while( input < NO || YES < input );
        
        if( input == NO ){
            this.setPreviousScreen();
            return;
        }
        
        ProgressionSaver.save( this.state );
        // go to exit screen
    }
    
}
