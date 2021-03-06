package com.esgi;

import com.esgi.data.Event;
import com.esgi.modes.State;
import com.esgi.data.YearlyResults;

public class YearlyResultsScreen extends PlayScreen {
    private final static int CORRUPTION_MODE = 2;
    private final static int FEED_MODE = 1;
    private int mode;
    private YearlyResults results;

    public YearlyResultsScreen( State state ){
        super( state );
    }

    @Override
    void init() {
        System.out.print( "\n\nBilan\n" );
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

        this.choiceHandler.handleYearlyChoice( this.currentChoices.get( input - 1 ));
        this.printAllChoices();
    }

    private void endYearlyResults(){
        System.out.println( this.results.killingOrBirthingPeople() );
        this.setPreviousScreen();
    }

    private void printAllChoices()
    {
        Event yearlyEvent;
        if( this.mode == CORRUPTION_MODE ){
            yearlyEvent = this.results.buildCorruptEvent();
        } else {
            yearlyEvent = this.results.buildBuyFoodEvent();
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
        } while( input < FEED_MODE || 3 < input );

        if( input == 3 )
        {
            this.endYearlyResults();
            return;
        }
        this.mode = input;
        this.printAllChoices();
    }
}
