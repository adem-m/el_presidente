package com.esgi.modes;

import com.esgi.data.Event;
import com.esgi.data.enums.Difficulty;
import com.esgi.data.enums.Mode;

public class ScenarioState extends State {
    public ScenarioState(String scenarioName, Difficulty difficulty, Mode mode) {
        super(scenarioName, difficulty, mode);
        this.initializeAttributesFromScenario();
    }

    @Override
    public Event getNextEvent() {
        this.currentEvent = this.nextEvents.poll();
        return this.currentEvent;
    }

    private void initializeAttributesFromScenario() {
        System.out.println("\n\n" + this.getEventById(this.scenario.getFirstEventId()).getText() + "\n\n");
        this.nextEvents.offer(this.getEventById(this.scenario.getFirstEventId()));
    }

}
