package com.esgi;

import com.esgi.data.Event;
import com.esgi.data.State;
import com.esgi.data.YearlyResults;

public class YearlyResultsGameMode extends PlayGameMode {
    private Event yearlyEvent;
    private YearlyResults results;

    public YearlyResultsGameMode(State state ){
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
            System.out.println( this.results.killingOrBirthingPeople() );
            this.setPreviousGameMode();
            return;
        }

        this.printAllChoices();
    }

    private void printAllChoices()
    {
        this.printEvent( this.yearlyEvent );
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
        System.out.printf( "1 - %s\n", "Nourrir le peuple" );
        System.out.printf( "2 - %s\n", "¡ Corrupción absoluta !" );

        int input;
        do{
            input = this.inputHandler.getUserInput();
        } while( input != 1 && input != 2 );

        this.yearlyEvent = input == 1 ? results.getBuyFoodEvent() : results.getCorruptEvent();
        this.printAllChoices();
    }
}
