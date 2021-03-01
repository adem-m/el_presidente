package com.esgi;

import com.esgi.builders.StateBuilder;
import com.esgi.data.enums.Difficulty;

public class DifficultyScreen extends Screen {
    private final StateBuilder stateBuilder;
    private int returnOption;

    public DifficultyScreen(StateBuilder stateBuilder) {
        this.stateBuilder = stateBuilder;
    }

    @Override
    void handleInput() {
        int input;

        do {
            input = this.inputHandler.getUserInput();
        } while( input < 1 || this.returnOption < input );

        if ( input == this.returnOption ) {
            this.setPreviousScreen();
            return;
        }

        Difficulty difficulty = Difficulty.fromId(input);
        if (difficulty != null) {
            this.switchScreen(
                new PlayScreen(
                    this.stateBuilder.setDifficulty(difficulty).build() ));
        } else {
            System.out.println("Fermeture...");
            System.exit(0);
        }
    }

    @Override
    void init() {
        System.out.println("\n\nVeuillez choisir un niveau de difficulté :");

        int index = 1;
        for (Difficulty difficulty : Difficulty.values()) {
            System.out.printf("%d - %s%n", index, difficulty);
            index += 1;
        }

        this.returnOption = index;
        System.out.printf("%d - %s%n", this.returnOption, "Retour à la sélection de scénario");
    }
}
