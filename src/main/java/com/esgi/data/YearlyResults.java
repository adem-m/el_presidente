package com.esgi.data;

import java.util.*;

public class YearlyResults {
    private final Event corruptEvent;
    private final Event buyFoodEvent;
    private final State state;

    public YearlyResults(State state) {
        this.state = state;
        this.corruptEvent = buildCorruptEvent();
        this.buyFoodEvent = buildBuyFoodEvent();
    }

    private int calculateMoneyRaise() {
        return this.state.getAttributes().get("industry") * 10;
    }

    public String generateMoneyRaiseLabel() {
        return String.format("Votre industrie a rapporté %d$ cette année.\n", calculateMoneyRaise());
    }

    private int calculateFoodRaise() {
        return this.state.getAttributes().get("agriculture") * 40;
    }

    public String generateFoodRaiseLabel() {
        return String.format("Votre agriculture a rapporté %d unités de nourriture cette année.\n", calculateFoodRaise());
    }

    public void applyFoodAndMoneyRaises() {
        this.state.getAttributes().put(
                "food",
                this.state.getAttributes().get("food") + calculateFoodRaise()
        );
        this.state.getAttributes().put(
                "money",
                this.state.getAttributes().get("money") + calculateMoneyRaise()
        );
    }

    private String generateFactionDetails(Faction faction) {
        return String.format(
                "%s : %d partisans, %d%% de satisfaction\nCoût : %d$",
                faction.getName(),
                faction.getPopulation(),
                faction.getSatisfaction(),
                calculateCorruptionCost(faction)
        );
    }

    private long calculateCorruptionCost(Faction faction) {
        return faction.getPopulation() * 15L;
    }

    private Event buildCorruptEvent() {
        List<EventChoice> choices = new ArrayList<>();
        for (Map.Entry<String, Faction> faction : this.state.getFactions().entrySet()) {
            if (!faction.getValue().getName().equals("loyalists")) {
                choices.add(new EventChoice(
                        generateFactionDetails(faction.getValue()),
                        Arrays.asList(
                                new Effect(
                                        "faction",
                                        faction.getValue().getName(),
                                        "satisfaction",
                                        10L,
                                        "flat",
                                        "bonus"
                                ),
                                new Effect(
                                        "faction",
                                        "loyalists",
                                        "satisfaction",
                                        (calculateCorruptionCost(faction.getValue()) / 10) * -1,
                                        "flat",
                                        "malus"
                                ),
                                new Effect(
                                        "attribute",
                                        "money",
                                        (calculateCorruptionCost(faction.getValue())) * -1,
                                        "flat",
                                        "malus"
                                )
                        )));
            }
        }
        return new Event("Quelle faction souhaitez vous corrompre ?", choices);
    }

    private Event buildBuyFoodEvent() {
        return null;
    }
}
