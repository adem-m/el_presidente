package com.esgi;

import com.esgi.builders.StateBuilder;
import com.esgi.data.enums.Difficulty;

public class DifficultyGameMode extends GameMode {
    private final StateBuilder stateBuilder;

    public DifficultyGameMode(StateBuilder stateBuilder) {
        this.stateBuilder = stateBuilder;
    }

    @Override
    void handleInput() {
        int input;
        int returnInput = Difficulty.values().length + 1;
        do {
            input = this.inputHandler.getUserInput();
        } while( input < 0 || returnInput < input );

        if ( input == returnInput ) {
            this.setPreviousGameMode();
            return;
        }

        Difficulty difficulty = Difficulty.fromId(input);
        if (difficulty != null) {
            this.switchGameMode(
                    new PlayGameMode(
                            this.stateBuilder.setDifficulty(difficulty).build() ));
        } else {
            System.out.println("Fermeture...");
            System.exit(0);
        }
    }

    @Override
    void init() {
        System.out.println("Veuillez choisir un niveau de difficulté :\n");

        int index = 1;
        for (Difficulty difficulty : Difficulty.values()) {
            System.out.printf("%d - %s%n", index, difficulty);
            index += 1;
        }
        System.out.printf("%d - %s%n", index, "Retour à la sélection de scénario");
    }
}
