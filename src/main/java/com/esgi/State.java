package com.esgi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class State {
    private final static String SCENARIOS_FILE_NAME = "scenarios.json";
    private final static String EVENTS_FILE_NAME = "events.json";

    private final Map<String, Integer> attributes = new HashMap<>();
    private final Map<String, Faction> factions = new HashMap<>();
    private final List<Event> events = new ArrayList<>();
    private final List<Event> nextEvents = new ArrayList<>();
    private int turnCount;
    private Season startingSeason;

    public void initialize(String scenarioName) {
        initializeEvents();
        Scenario scenario = fetchScenarioFromName(scenarioName);
        initializeAttributesFromScenario(scenario);
        turnCount = 0;
        startingSeason = Season.fromId(new Random().nextInt(4));
    }

    private Scenario fetchScenarioFromName(String scenarioName) {
        JSONObject jsonObject = new JSONHandler().getObjectFromJSON(SCENARIOS_FILE_NAME);
        JSONObject jsonScenario = (JSONObject) ((JSONObject) jsonObject.get("scenarios")).get(scenarioName);
        return new Scenario(
                scenarioName,
                (Long) jsonScenario.get("industry"),
                (Long) jsonScenario.get("agriculture"),
                (Long) jsonScenario.get("money"),
                (Long) jsonScenario.get("firstEventId"),
                (JSONArray) jsonScenario.get("factions")
        );
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

    private void initializeEvents() {
        JSONObject jsonObject = new JSONHandler().getObjectFromJSON(EVENTS_FILE_NAME);
        JSONArray events = (JSONArray) jsonObject.get("events");
        for (Object object : events) {
            JSONObject event = (JSONObject) object;
            this.events.add(
                    new Event(
                            (long) event.get("id"),
                            (String) event.get("text"),
                            (JSONArray) event.get("seasons"),
                            (JSONArray) event.get("choices")));
        }
    }

    private Event getEventById(int id) throws Exception {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        throw new Exception("Event " + id + " not found");
    }
}
