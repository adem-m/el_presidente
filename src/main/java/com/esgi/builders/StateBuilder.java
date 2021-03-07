package com.esgi.builders;

import com.esgi.modes.SandboxState;
import com.esgi.modes.ScenarioState;
import com.esgi.modes.State;
import com.esgi.data.enums.Difficulty;
import com.esgi.data.enums.Mode;

public class StateBuilder {
    private String scenarioName;
    private Difficulty difficulty;
    private Mode mode;

    public StateBuilder setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
        return this;
    }

    public StateBuilder setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public StateBuilder setMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public State build() throws Error {
        switch (this.mode) {
            case SANDBOX:
                return new SandboxState(this.scenarioName, this.difficulty, this.mode);
            case SCENARIO:
                return new ScenarioState(this.scenarioName, this.difficulty, this.mode);
            default:
                throw new Error("Mode inconnu");
        }
    }
}
