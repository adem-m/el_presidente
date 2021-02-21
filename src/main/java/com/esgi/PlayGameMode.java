package com.esgi;

import com.esgi.builders.StateBuilder;
import com.esgi.data.Event;
import com.esgi.data.EventChoice;
import com.esgi.data.State;
import com.esgi.data.YearlyResults;

import java.util.List;

public class PlayGameMode extends GameMode {
    protected final State state;
    protected List<EventChoice> currentChoices;

    public PlayGameMode(State state) {
        this.state = state;
    }

    @Override
    void init() {
        this.printEvent( this.state.getNextEvent() );
    }

    @Override
    void handleInput() {
        int input = this.inputHandler.getUserInput();
        this.state.handleChoice(
                this.currentChoices.get(input - 1));

        this.update();
    }

    protected void printEvent(Event currentEvent) {
        System.out.printf( "\n\n%s\n", currentEvent.getText());
        this.printChoices(currentEvent);
    }

    protected void printChoices(Event event) {
        this.currentChoices = event.getChoices();
        int index = 0;
        for (EventChoice choice : this.currentChoices) {
            System.out.printf(
                    "%d - %s%n",
                    ++index,
                    choice.generateLabel(this.state.getDifficulty())
            );
        }
    }

    private void update()
    {
        if( this.state.isGameLost() )
        {
            System.out.println( "Partie terminée..." );
            this.switchGameMode( new MainTitleGameMode() );
            return;
        }

        if( this.state.hasYearPassed() )
        {
            this.switchGameMode( new YearlyResultsGameMode( this.state ));
        }
        else
        {
            this.printEvent(this.state.getNextEvent());
        }
    }
}
