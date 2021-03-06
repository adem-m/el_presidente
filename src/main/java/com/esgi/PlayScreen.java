package com.esgi;

import com.esgi.data.DisplayGenerator;
import com.esgi.data.Event;
import com.esgi.data.EventChoice;
import com.esgi.game.ChoiceHandler;
import com.esgi.modes.State;

import java.util.List;

public class PlayScreen extends Screen {
    protected final State state;
    protected List<EventChoice> currentChoices;
    protected ChoiceHandler choiceHandler;
    protected DisplayGenerator display;
    private int pauseOption;
    private Event currentEvent;

    public PlayScreen( State state ){
        this.state = state;
        this.choiceHandler = new ChoiceHandler( this.state );
        this.display = new DisplayGenerator( this.state );
    }

    @Override
    void init() {
        if (this.state.isGameLost()) {
            System.out.println("Partie terminée...");
            this.switchScreen(new MainTitleScreen());
            return;
        }

        Event currentEvent = this.state.getLastEvent();
        if( currentEvent != null ){
            this.currentEvent = currentEvent;
            this.printEvent( currentEvent );
            this.state.setLastEvent( null );
            return;
        }

        if( this.state.isGameEnded() ){
            System.out.println( "Félicitations, jeu terminé !" );
            this.switchToMainScreen();
            return;
        }

        this.currentEvent = this.state.getNextEvent();
        this.printEvent( this.currentEvent );
    }

    @Override
    void handleInput() {
        int input;
        do {
            input = this.inputHandler.getUserInput();
        } while (input < 1 || this.pauseOption < input );

        if( input == this.pauseOption ){
            this.state.setLastEvent( this.currentEvent );
            this.switchScreen( new PauseScreen( this.state ));
            return;
        }

        this.choiceHandler.handle(
            this.currentChoices.get( input - 1 ));

        this.update();
    }

    protected void printEvent(Event currentEvent) {
        System.out.println( this.display.generateStateDisplay() );
        System.out.printf("\n\n%s\n", currentEvent.getText());
        this.printChoices(currentEvent);
    }

    protected void printChoices(Event event) {
        this.currentChoices = event.getChoices();
        int index = 0;
        for (EventChoice choice : this.currentChoices) {
            System.out.printf(
                "%d - %s\n",
                ++index,
                choice.generateLabel(this.state.getDifficulty())
            );
        }

        this.pauseOption = ++index;
        System.out.printf(
            "%d - %s\n",
            this.pauseOption,
            "PAUSE"
        );
    }

    private void update() {
        if (this.state.hasYearPassed()) {
            this.switchScreen(new YearlyResultsScreen(this.state));
            return;
        }

        if( this.state.isGameEnded() ){
            System.out.println( "Félicitations, jeu terminé !" );
            this.switchToMainScreen();
            return;
        }
        
        this.currentEvent = this.state.getNextEvent();
        this.printEvent( this.currentEvent );
    }
}
