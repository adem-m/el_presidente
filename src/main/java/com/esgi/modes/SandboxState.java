package com.esgi.modes;

import java.util.Random;

import com.esgi.data.Event;
import com.esgi.data.enums.Difficulty;
import com.esgi.data.enums.Mode;

public class SandboxState extends State {

    public SandboxState(String scenarioName, Difficulty difficulty, Mode mode) {
        super(scenarioName, difficulty, mode);
    }

    @Override
    public Event getNextEvent() throws Error {
        int maxAttempts = 10;
        Random generator = new Random();
        Object[] values = this.events.values().toArray();

        do {
            if (maxAttempts-- <= 0) {
                throw new Error("Temps de calcul dépassé !");
            }

            this.currentEvent = (Event) values[generator.nextInt(values.length)];
        } while (!this.currentEvent.getSeasons().contains(getCurrentSeason()));
        return this.currentEvent;
    }
}
