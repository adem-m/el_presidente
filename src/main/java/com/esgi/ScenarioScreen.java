package com.esgi;

import com.esgi.builders.StateBuilder;
import com.esgi.data.Loader;
import com.esgi.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class ScenarioScreen extends Screen {
    private final List<String> choices = new ArrayList<>();
    private final StateBuilder stateBuilder;
    private int returnOption;

    public ScenarioScreen( StateBuilder stateBuilder ){
        this.stateBuilder = stateBuilder;
    }

    @Override
    void init() {
        int index = 0;
        for (Pair scenario : Loader.fetchScenariosList()) {
            System.out.printf("%d - %s%n", ++index, scenario.getValue());
            this.choices.add(scenario.getKey());
        }
        this.returnOption = ++index;
        System.out.printf("%d - %s%n", this.returnOption, "Retour" );
    }

    @Override
    void handleInput() {
        int input;
        do{
            input = this.inputHandler.getUserInput();
        } while( input < 1 || this.choices.size() < input );
        
        if( input == this.returnOption ){
            this.setPreviousScreen();
            return;
        }

        this.switchScreen(
            new DifficultyScreen(
                this.stateBuilder.setScenarioName( this.choices.get( input - 1 ))));
        
    }
}
