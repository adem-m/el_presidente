package com.esgi.data;

import java.util.Map;

public class DisplayGenerator {
    private final State state;

    public DisplayGenerator(State state) {
        this.state = state;
    }

    public String generateStateDisplay() {
        return generateTurnDisplay() +
                generateSeasonDisplay() +
                "\n" +
                generateAttributesDisplay() +
                "\n" +
                generateFactionsDisplay();
    }

    private String generateTurnDisplay() {
        return "Tour " + this.state.getTurnCount() + "\n";
    }

    private String generateSeasonDisplay() {
        return this.state.getCurrentSeason().getName() + "\n";
    }

    private String generateAttributesDisplay() {
        return "Industrie : " +
                this.state.getAttributes().get("industry") + " %\n" +
                "Agriculture : " +
                this.state.getAttributes().get("agriculture") + " %\n" +
                "Argent : " +
                this.state.getAttributes().get("money") + " $\n" +
                "Nourriture : " +
                this.state.getAttributes().get("food") + " unité(s)\n";
    }

    private String generateFactionsDisplay() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Faction> faction : this.state.getFactions().entrySet()) {
            stringBuilder
                    .append(capitalizeFirstLetter(faction.getValue().getName()))
                    .append(" - ")
                    .append(faction.getValue().getPopulation())
                    .append(" partisans, ")
                    .append(faction.getValue().getSatisfaction())
                    .append(" % de satisfaction\n");
        }
        return stringBuilder.toString();
    }

    private String capitalizeFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
