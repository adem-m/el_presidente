package com.esgi;

import com.esgi.builders.StateBuilder;
import com.esgi.data.enums.Difficulty;

public class DifficultyScreen extends Screen {
    private final StateBuilder stateBuilder;

    public DifficultyScreen(StateBuilder stateBuilder) {
        this.stateBuilder = stateBuilder;
    }

    @Override
    void handleInput() {
        int input;
        int returnInput = Difficulty.values().length + 1;
        do {
            input = this.inputHandler.getUserInput();
        } while( input < 1 || returnInput < input );

        if ( input == returnInput ) {
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
        System.out.printf("%d - %s%n", index, "Retour à la sélection de scénario");
    }
}
