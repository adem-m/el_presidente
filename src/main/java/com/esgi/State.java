package com.esgi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class State {
    private final Map<String, Integer> attributes = new HashMap<>();
    private final Map<String, Faction> factions = new HashMap<>();
    private final List<Event> events = new ArrayList<>();
    private final List<Event> nextEvents = new ArrayList<>();
    private int turnCount;
    private Season startingSeason;

    public Map<String, Integer> getAttributes() {
        return attributes;
    }

    public Map<String, Faction> getFactions() {
        return factions;
    }

    public List<Event> getEvents() {
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
        this.events.addAll(Loader.fetchEvents());
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

    private Event getEventById(int id) throws Exception {
        for (Event event : this.events) {
            if (event.getId() == id) {
                return event;
            }
        }
        throw new Exception("Event " + id + " not found");
    }
}
