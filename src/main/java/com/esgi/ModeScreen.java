package com.esgi;

import com.esgi.builders.StateBuilder;
import com.esgi.data.enums.Mode;

public class ModeScreen extends Screen {
    private final StateBuilder stateBuilder;

    public ModeScreen(){
        this.stateBuilder = new StateBuilder();
    }

    @Override
    void init() {
        System.out.println("\n\nVeuillez choisir un mode de jeu :");

        int index = 1;
        for( Mode mode : Mode.values() ){
            System.out.printf("%d - %s%n", index, mode );
            index += 1;
        }
        System.out.printf("%d - %s%n", index, "Fermer");
    }

    @Override
    void handleInput() {
        int input;
        int returnInput = Mode.values().length + 1;
        
        do {
            input = this.inputHandler.getUserInput();
        } while( input < 1 || returnInput < input );

        if ( input == returnInput ) {
            System.exit( 0 );
            return;
        }

        Mode mode = Mode.fromId( input );
        this.switchScreen(
            new ScenarioScreen(
                this.stateBuilder.setMode( mode ) ));
    }
    
}
