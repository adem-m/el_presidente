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

    public Event getCorruptEvent() {
        return corruptEvent;
    }

    public Event getBuyFoodEvent() {
        return buyFoodEvent;
    }

    private int calculateMoneyRaise() {
        return this.state.getAttributes().get("industry") * 10;
    }

    public String generateMoneyRaiseLabel() {
        return String.format("Votre industrie a rapporté %d$ cette année.", calculateMoneyRaise());
    }

    private int calculateFoodRaise() {
        return this.state.getAttributes().get("agriculture") * 40;
    }

    public String generateFoodRaiseLabel() {
        return String.format("Votre agriculture a rapporté %d unités de nourriture cette année.", calculateFoodRaise());
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
        return new Event(
                "Acheter de la nourriture pour vos habitants",
                Arrays.asList(
                        new EventChoice(
                                "Acheter 1 unité de nourriture\nCoût : 8$",
                                Arrays.asList(
                                        new Effect(
                                                "attribute",
                                                "food",
                                                1L,
                                                "flat",
                                                "bonus"
                                        ),
                                        new Effect(
                                                "attribute",
                                                "money",
                                                -8L,
                                                "flat",
                                                "malus"
                                        )
                                )
                        ),
                        new EventChoice(
                                "Acheter assez de nourriture pour tout le monde\nCoût : %d$",
                                Arrays.asList(
                                        new Effect(
                                                "attribute",
                                                "money",
                                                calculateLackOfFood() * -8,
                                                "flat",
                                                "malus"
                                        ),
                                        new Effect(
                                                "attribute",
                                                "food",
                                                calculateLackOfFood(),
                                                "flat",
                                                "bonus"
                                        )
                                )
                        )
                )
        );
    }

    private long calculateLackOfFood() {
        long need = this.state.calculateTotalPopulation() * 4L;
        return this.state.getAttributes().get("food") < need ?
                need - this.state.getAttributes().get("food") :
                0;
    }

    public void end() {
        int lackOfFood = eat();
        if (lackOfFood > 0) {
            killPeople(lackOfFood);
        } else {
            giveBirth();
        }
    }

    private int eat() {
        long lackOfFood = calculateLackOfFood();
        if (lackOfFood > 0) {
            this.state.getAttributes().put("food", 0);
            return (int) lackOfFood;
        }
        this.state.getAttributes().put(
                "food",
                this.state.getAttributes().get("food") - (this.state.calculateTotalPopulation() * 4));
        return 0;
    }

    private void giveBirth() {
        int births = calculateBirthsNumber();
        while (births > 0) {
            this.state.getRandomFaction().modifyPopulation(1);
            births--;
        }
    }

    private int calculateBirthsNumber() {
        return Math.round((this.state.calculateTotalPopulation() * 0.01f) * (new Random().nextInt(10) + 1));
    }

    private void killPeople(int foodRest) {
        int deaths = foodRest / 4;
        while (deaths > 0) {
            Faction faction = this.state.getRandomFaction();
            if (faction.getPopulation() > 0) {
                faction.modifyPopulation(-1);
                deaths--;
            }
        }
    }
}
