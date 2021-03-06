package com.esgi.modes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import com.esgi.data.Event;
import com.esgi.data.Faction;
import com.esgi.data.Loader;
import com.esgi.data.Scenario;
import com.esgi.data.enums.Mode;
import com.esgi.data.enums.Season;
import com.esgi.data.enums.Difficulty;

public abstract class State implements Serializable {
    private static final long serialVersionUID = 1234567L;

    protected final Map<String, Integer> attributes = new HashMap<>();
    protected final Map<String, Faction> factions = new HashMap<>();
    protected final Map<Integer, Event> events = new HashMap<>();
    protected final Queue<Event> nextEvents = new LinkedList<>();
    protected final Mode mode;
    protected final Season startingSeason;
    protected final Difficulty difficulty;
    protected final Scenario scenario;
    protected int turnCount = 0;

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public Map<String, Integer> getAttributes() {
        return this.attributes;
    }

    public Map<String, Faction> getFactions() {
        return this.factions;
    }

    public Map<Integer, Event> getEvents() {
        return this.events;
    }

    public Queue<Event> getNextEvents() {
        return this.nextEvents;
    }

    public int getTurnCount() {
        return this.turnCount;
    }

    public void incrementTurnCount() {
        this.turnCount++;
    }

    public boolean hasYearPassed() {
        return this.turnCount % 4 == 0;
    }

    public Season getStartingSeason() {
        return this.startingSeason;
    }

    public String getScenarioName() {
        return this.scenario.getName();
    }

    public State(String scenarioName, Difficulty difficulty, Mode mode) {
        this.difficulty = difficulty;
        this.mode = mode;
        this.events.putAll(Loader.fetchEvents(scenarioName));
        this.scenario = Loader.fetchScenarioFromName(scenarioName);
        System.out.println("\n\nScenar" + this.scenario.getName() + "\n\n");
        this.initializeAttributesFromScenario();
        this.startingSeason = Season.fromId(new Random().nextInt(4));
    }

    private void initializeAttributesFromScenario() {
        this.attributes.put("industry", this.scenario.getIndustry());
        this.attributes.put("agriculture", this.scenario.getAgriculture());
        this.attributes.put("money", this.scenario.getMoney());
        this.attributes.put("food", this.scenario.getFood());

        for (Faction faction : this.scenario.getFactions()) {
            this.factions.put(faction.getName(), faction);
        }
    }

    public abstract Event getNextEvent();

    public Event getEventById(int id) {
        return this.events.get(id);
    }

    public Season getCurrentSeason() {
        return Season.fromId((this.turnCount + this.startingSeason.getId()) % 4);
    }

    public int calculateGlobalSatisfaction() {
        int satisfaction = 0;
        int population = 0;

        for (Map.Entry<String, Faction> faction : this.factions.entrySet()) {
            satisfaction += faction.getValue().getSatisfaction() * faction.getValue().getPopulation();
            population += faction.getValue().getPopulation();
        }

        if (population == 0) {
            return 0;
        }

        return satisfaction / population;
    }

    public int calculateTotalPopulation() {
        int population = 0;

        for (Map.Entry<String, Faction> faction : this.factions.entrySet()) {
            population += faction.getValue().getPopulation();
        }

        return population;
    }

    public boolean isGameLost() {
        return calculateGlobalSatisfaction() < this.difficulty.getMinimumGlobalSatisfaction() ||
                calculateTotalPopulation() == 0;
    }

    public Faction getRandomFaction() {
        List<String> factionKeys = new ArrayList<>(this.factions.keySet());
        return this.factions.get(
                factionKeys.get(
                        new Random().nextInt(factionKeys.size())));
    }
}
