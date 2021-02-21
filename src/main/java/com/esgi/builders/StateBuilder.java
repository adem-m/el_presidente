package com.esgi.builders;

import com.esgi.data.State;
import com.esgi.data.enums.Difficulty;

public class StateBuilder {
    private String scenarioName;
    private Difficulty difficulty;

    public StateBuilder setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
        return this;
    }

    public StateBuilder setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public State build() {
        return new State(this.scenarioName, this.difficulty);
    }
}
