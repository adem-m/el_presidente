package com.esgi.data;

import com.esgi.data.enums.Season;
import com.esgi.game.ChoiceHandler;

import java.util.*;

public class State {
    private final Map<String, Integer> attributes = new HashMap<>();
    private final Map<String, Faction> factions = new HashMap<>();
    private final Map<Integer, Event> events = new HashMap<>();
    private final List<Event> nextEvents = new ArrayList<>();
    private int turnCount;
    private Season startingSeason;
    private ChoiceHandler choiceHandler;

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

    public void initialize(String scenarioName) {
        this.choiceHandler = new ChoiceHandler(this);
        this.events.putAll(Loader.fetchEvents(scenarioName));
        Scenario scenario = Loader.fetchScenarioFromName(scenarioName);
        initializeAttributesFromScenario(scenario);
        turnCount = 0;
        startingSeason = Season.fromId(new Random().nextInt(4));
    }

    private void initializeAttributesFromScenario(Scenario scenario) {
        if (!scenario.getName().equals("sandbox")) {
            try {
                nextEvents.add(getEventById(scenario.getFirstEventId()));
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        attributes.put("industry", scenario.getIndustry());
        attributes.put("agriculture", scenario.getAgriculture());
        attributes.put("money", scenario.getMoney());
        for (Faction faction : scenario.getFactions()) {
            factions.put(faction.getName(), faction);
        }
    }

    public Event getEventById(int id) {
        return this.events.get(id);
    }

    public void handleChoice(EventChoice choice) {
        this.choiceHandler.handle(choice);
    }
}
