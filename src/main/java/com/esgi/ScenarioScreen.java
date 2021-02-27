package com.esgi;

import com.esgi.builders.StateBuilder;
import com.esgi.data.Loader;
import com.esgi.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class ScenarioScreen extends Screen {
    private final List<String> choices = new ArrayList<>();

    @Override
    void init() {
        int index = 0;
        for (Pair scenario : Loader.fetchScenariosList()) {
            System.out.printf("%d - %s%n", ++index, scenario.getValue());
            this.choices.add(scenario.getKey());
        }
        this.choices.add("exit");
        System.out.printf("%d - %s%n", ++index, "exit");
    }

    @Override
    void handleInput() {
        int input;
        do{
            input = this.inputHandler.getUserInput();
        } while( input < 1 || this.choices.size() < input );


        String value = this.choices.get(input - 1);
        if (value.equals("exit")) {
            System.out.println("Fermeture...");
            System.exit(0);
        } else {
            this.switchGameMode(
                    new DifficultyScreen(
                            new StateBuilder().setScenarioName(this.choices.get(input - 1))));
        }
    }
}
