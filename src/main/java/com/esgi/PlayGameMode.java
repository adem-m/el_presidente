package com.esgi;

import com.esgi.builders.StateBuilder;
import com.esgi.data.Event;
import com.esgi.data.EventChoice;
import com.esgi.data.State;

import java.util.List;

public class PlayGameMode extends GameMode {
    private final State state;
    private List<EventChoice> currentChoices;

    public PlayGameMode(StateBuilder statebuilder) {
        this.state = statebuilder.build();
    }

    @Override
    void init() {
        System.out.println("Game initiated");
        Event currentEvent = this.state.getNextEvent();
        System.out.println(currentEvent.getText());
        this.printChoices(currentEvent);
    }

    @Override
    void handleInput() {
        int input = this.inputHandler.getUserInput();
        this.state.handleChoice(
                this.currentChoices.get(input - 1));
        this.printEvent(this.state.getNextEvent());
    }

    private void printEvent(Event currentEvent) {
        System.out.println(currentEvent.getText());
        this.printChoices(currentEvent);
    }

    private void printChoices(Event event) {
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
}
