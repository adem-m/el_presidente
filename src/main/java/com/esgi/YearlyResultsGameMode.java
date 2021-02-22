package com.esgi;

import com.esgi.data.Event;
import com.esgi.data.State;
import com.esgi.data.YearlyResults;

public class YearlyResultsGameMode extends PlayGameMode {
    private final static int CORRUPTION_MODE = 2;
    private final static int FEED_MODE = 1;
    private int mode;
    private YearlyResults results;

    public YearlyResultsGameMode( State state ){
        super( state );
    }

    @Override
    void init() {
        System.out.printf( "\n\nBilan\n" );
        this.yearlyResultHandler();
    }

    @Override
    void handleInput() {
        int input;
        int returnInput = this.currentChoices.size() + 1;
        do {
            input = this.inputHandler.getUserInput();
        } while( input < 1 || returnInput < input );

        if( input == returnInput )
        {
            this.endYearlyResults();
            return;
        }

        this.state.handleYearlyChoice( this.currentChoices.get( input - 1 ));
        this.printAllChoices();
    }

    private void endYearlyResults(){
        System.out.println( this.results.killingOrBirthingPeople() );
        this.setPreviousGameMode();
    }

    private void printAllChoices()
    {
        Event yearlyEvent;
        if( this.mode == CORRUPTION_MODE ){
            yearlyEvent = this.results.getCorruptEvent();
        } else {
            yearlyEvent = this.results.getBuyFoodEvent();
        }

        this.printEvent( yearlyEvent );
        System.out.printf( "%d - %s\n", this.currentChoices.size() + 1, "Retour au jeu" );
    }

    private void yearlyResultHandler()
    {
        this.results = new YearlyResults( this.state );
        System.out.printf( "\n\n%s\n", this.results.generateFoodRaiseLabel() );
        System.out.printf( "%s\n", this.results.generateMoneyRaiseLabel() );
        this.results.applyFoodAndMoneyRaises();
        this.corruptionOrFoodChoice();
    }

    private void corruptionOrFoodChoice()
    {
        System.out.printf( "%d - %s\n", FEED_MODE, "Nourrir le peuple" );
        System.out.printf( "%d - %s\n", CORRUPTION_MODE,  "¡ Corrupción absoluta !" );
        System.out.printf( "3 - %s\n", "¡ Adelante Señor Presidente ! (Continuer)" );

        int input;
        do{
            input = this.inputHandler.getUserInput();
        } while( input < CORRUPTION_MODE || 3 < input );

        if( input == 3 )
        {
            this.endYearlyResults();
            return;
        }
        this.mode = input;
        this.printAllChoices();
    }
}
