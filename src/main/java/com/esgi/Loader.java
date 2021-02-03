package com.esgi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Loader {
    private final static String SCENARIOS_FILE_NAME = "scenarios.json";
    private final static String EVENTS_FILE_NAME = "events.json";

    public static Scenario fetchScenarioFromName(String scenarioName) {
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

    public static List<Event> fetchEvents() {
        List<Event> events = new ArrayList<>();
        JSONObject jsonObject = new JSONHandler().getObjectFromJSON(EVENTS_FILE_NAME);
        JSONArray JsonEvents = (JSONArray) jsonObject.get("events");
        for (Object object : JsonEvents) {
            JSONObject event = (JSONObject) object;
            events.add(
                    new Event(
                            (long) event.get("id"),
                            (String) event.get("text"),
                            (JSONArray) event.get("seasons"),
                            (JSONArray) event.get("choices")));
        }
        return events;
    }
}
