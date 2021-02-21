package com.esgi;

public class MainTitleGameMode extends GameMode {
    @Override
    void init() {
        System.out.println("\n\n¡ Bienvenido en El Presidente !\n\nAppuyez sur 'Entrée' pour continuer'...");
    }

    @Override
    void handleInput() {
        this.inputHandler.expectEnter();
        this.switchGameMode(new ScenarioGameMode());
    }
}
