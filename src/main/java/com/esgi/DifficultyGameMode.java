package com.esgi;
import com.esgi.builders.StateBuilder;
import com.esgi.data.enums.Difficulty;

public class DifficultyGameMode extends GameMode {
    private StateBuilder stateBuilder;

    public DifficultyGameMode( StateBuilder stateBuilder )
    {
        super();
        this.stateBuilder = stateBuilder;
    }

    @Override
    void handleInput() {
        int input = this.inputHandler.getUserInput();
        if( input == 4 ){
            this.setPreviousGameMode();
            return;
        }

        Difficulty difficulty = Difficulty.fromId( input );
        if( difficulty != null ){
            System.out.println( difficulty );
        } else {
            System.out.println( "Fermeture..." );
            System.exit( 0 );
        }
    }

    @Override
    void init() {
        System.out.println( "Veuillez choisir un niveau de difficulté:\n" );

        int index = 1;
        for( Difficulty difficulty: Difficulty.values() ){
            System.out.println( String.format( "%d - %s", index, difficulty ));
            index += 1;
        }
        System.out.println( String.format( "%d - %s", index, "Retour à l'écran titre" ));
    }    
}
