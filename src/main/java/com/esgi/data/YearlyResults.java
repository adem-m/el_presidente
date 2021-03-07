package com.esgi.data;

import java.util.*;

public class YearlyResults {
    final static int INDUSTRY_MULTIPLIER = 10;
    final static int AGRICULTURE_MULTIPLIER = 40;
    final static long SINGLE_CORRUPTION_COST = 15;
    final static long SATISFACTION_RAISE_WHEN_CORRUPTED = 10;
    final static long SINGLE_FOOD_COST = 8;
    final static int FOOD_NEED_PER_PERSON = 4;
    final static int MINIMUM_BIRTHING_RATE = 1;
    final static int MAXIMUM_BIRTHING_RATE = 10;

    private final State state;

    public YearlyResults(State state) {
        this.state = state;
    }

    private int calculateMoneyRaise() {
        return this.state.getAttributes().get("industry") * INDUSTRY_MULTIPLIER;
    }

    public String generateMoneyRaiseLabel() {
        return String.format("Votre industrie a rapporté %d$ cette année.", calculateMoneyRaise());
    }

    private int calculateFoodRaise() {
        return this.state.getAttributes().get("agriculture") * AGRICULTURE_MULTIPLIER;
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
                "%s : %d Partisans | Satisfaction %d / 100\nCoût %d$",
                faction.getName(),
                faction.getPopulation(),
                faction.getSatisfaction(),
                calculateCorruptionCost(faction)
        );
    }

    private long calculateCorruptionCost(Faction faction) {
        return faction.getPopulation() * SINGLE_CORRUPTION_COST;
    }

    public Event buildCorruptEvent() {
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
                                        SATISFACTION_RAISE_WHEN_CORRUPTED,
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

    public Event buildBuyFoodEvent() {
        return new Event(
                "Acheter de la nourriture pour vos habitants",
                Arrays.asList(
                        new EventChoice(
                                "Acheter 1 unité de nourriture\nArgent : -8$",
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
                                                SINGLE_FOOD_COST * -1,
                                                "flat",
                                                "malus"
                                        )
                                )
                        ),
                        new EventChoice(
                                "Acheter assez de nourriture pour tout le monde\nArgent : %d$",
                                Arrays.asList(
                                        new Effect(
                                                "attribute",
                                                "money",
                                                calculateLackOfFood() * SINGLE_FOOD_COST * -1,
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
        long need = this.state.calculateTotalPopulation() * (long) FOOD_NEED_PER_PERSON;
        return this.state.getAttributes().get("food") < need ?
                need - this.state.getAttributes().get("food") :
                0;
    }

    public String killingOrBirthingPeople() {
        int lackOfFood = feedPopulation();
        if (lackOfFood > 0) {
            return killPeople(lackOfFood);
        }
        return giveBirth();
    }

    private int feedPopulation() {
        long lackOfFood = calculateLackOfFood();
        if (lackOfFood > 0) {
            this.state.getAttributes().put("food", 0);
            return (int) lackOfFood;
        }
        this.state.getAttributes().put(
                "food",
                this.state.getAttributes().get("food") - (this.state.calculateTotalPopulation() * FOOD_NEED_PER_PERSON));
        return 0;
    }

    private String giveBirth() {
        int births = calculateBirthsNumber();
        for (int i = 0; i < births; i++) {
            this.state.getRandomFaction().modifyPopulation(1);
        }
        return String.format("Votre abondance de nourriture a donné naissance à %d nouveau(x) partisan(s) !", births);
    }

    private int calculateBirthsNumber() {
        return Math.round((this.state.calculateTotalPopulation() * 0.01f) *
                (new Random().nextInt(MAXIMUM_BIRTHING_RATE) + MINIMUM_BIRTHING_RATE));
    }

    private String killPeople(int foodRest) {
        int deaths = foodRest / FOOD_NEED_PER_PERSON;
        for (int i = 0; i < deaths; i++) {
            Faction faction = this.state.getRandomFaction();
            if (faction.getPopulation() > 0) {
                faction.modifyPopulation(-1);
            } else {
                i--;
            }
        }
        return String.format("Par manque de nourriture, nous avons perdu %d partisan(s) cette année.", deaths);
    }
}
