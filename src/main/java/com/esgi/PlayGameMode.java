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
        if (this.state.isGameLost()) {
            System.out.println("Partie terminée...");
            this.switchGameMode(new MainTitleGameMode());
            return;
        }
        this.printEvent(this.state.getNextEvent());
    }

    @Override
    void handleInput() {
        int input;
        do {
            input = this.inputHandler.getUserInput();
        } while (input < 1 || this.currentChoices.size() < input);

        this.state.handleChoice(
                this.currentChoices.get(input - 1));

        this.update();
    }

    protected void printEvent(Event currentEvent) {
        System.out.println(this.state.generateStateDisplay());
        System.out.printf("\n\n%s\n", currentEvent.getText());
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

    private void update() {
        if (this.state.hasYearPassed()) {
            this.switchGameMode(new YearlyResultsGameMode(this.state));
        } else {
            this.printEvent(this.state.getNextEvent());
        }
    }
}
