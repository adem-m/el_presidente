package com.esgi.data;

import com.esgi.data.enums.Difficulty;
import com.esgi.data.enums.Season;
import com.esgi.game.ChoiceHandler;

import java.util.*;

public class State {
    private final Map<String, Integer> attributes = new HashMap<>();
    private final Map<String, Faction> factions = new HashMap<>();
    private final Map<Integer, Event> events = new HashMap<>();
    private final List<Event> nextEvents = new ArrayList<>();
    private int turnCount;
    private final Season startingSeason;
    private final ChoiceHandler choiceHandler;
    private boolean sandboxMode = false;
    private final Difficulty difficulty;

    public Map<String, Integer> getAttributes() {
        return attributes;
    }

    public Map<String, Faction> getFactions() {
        return factions;
    }

    public Map<Integer, Event> getEvents() {
        return events;
    }

    public List<Event> getNextEvents() {
        return nextEvents;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public Season getStartingSeason() {
        return startingSeason;
    }

    public State(String scenarioName, Difficulty difficulty) {
        this.difficulty = difficulty;
        this.choiceHandler = new ChoiceHandler(this);
        this.events.putAll(Loader.fetchEvents(scenarioName));
        Scenario scenario = Loader.fetchScenarioFromName(scenarioName);
        initializeAttributesFromScenario(scenario);
        startingSeason = Season.fromId(new Random().nextInt(4));
    }

    private void initializeAttributesFromScenario(Scenario scenario) {
        if (!scenario.getName().equals("sandbox")) {
            try {
                nextEvents.add(getEventById(scenario.getFirstEventId()));
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        } else {
            this.sandboxMode = true;
        }
        attributes.put("industry", scenario.getIndustry());
        attributes.put("agriculture", scenario.getAgriculture());
        attributes.put("money", scenario.getMoney());
        attributes.put("food", scenario.getFood());
        for (Faction faction : scenario.getFactions()) {
            factions.put(faction.getName(), faction);
        }
    }

    public Event getNextEvent() {
        if (sandboxMode) {
            return getRandomEvent();
        }
        if (!nextEvents.isEmpty()) {
            Event event = nextEvents.get(0);
            nextEvents.remove(0);
            return event;
        }
        turnSandboxMode();
        return getRandomEvent();
    }

    private void turnSandboxMode() {
        sandboxMode = true;
        events.clear();
        events.putAll(Loader.fetchEvents("sandbox"));
    }

    public Event getEventById(int id) {
        return this.events.get(id);
    }

    private Event getRandomEvent() {
        Random generator = new Random();
        Object[] values = events.values().toArray();
        Event event = (Event) values[generator.nextInt(values.length)];
        if (!event.getSeasons().contains(getCurrentSeason())) {
            return getRandomEvent();
        }
        return event;
    }

    public void handleChoice(EventChoice choice) {
        this.choiceHandler.handle(choice, this.difficulty);
        this.turnCount++;
    }

    private Season getCurrentSeason() {
        return Season.fromId((turnCount + startingSeason.getId()) % 4);
    }

    private int calculateGlobalSatisfaction() {
        int satisfaction = 0;
        int population = 0;
        for (Map.Entry<String, Faction> faction : this.factions.entrySet()) {
            satisfaction += faction.getValue().getSatisfaction() * faction.getValue().getPopulation();
            population += faction.getValue().getPopulation();
        }
        return satisfaction / population;
    }

    private int calculateTotalPopulation() {
        int population = 0;
        for (Map.Entry<String, Faction> faction : this.factions.entrySet()) {
            population += faction.getValue().getPopulation();
        }
        return population;
    }

    public boolean isGameLost() {
        return calculateGlobalSatisfaction() < this.difficulty.getMinimumGlobalSatisfaction();
    }
}
