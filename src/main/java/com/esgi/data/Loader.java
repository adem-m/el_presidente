package com.esgi.data;

import com.esgi.utils.JSONHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

    public static Map<Integer, Event> fetchEvents() {
        Map<Integer, Event> events = new HashMap<>();
        JSONObject jsonObject = new JSONHandler().getObjectFromJSON(EVENTS_FILE_NAME);
        JSONArray JsonEvents = (JSONArray) jsonObject.get("events");
        for (Object object : JsonEvents) {
            JSONObject JsonEvent = (JSONObject) object;
            Event event = new Event(
                    (long) JsonEvent.get("id"),
                    (String) JsonEvent.get("text"),
                    (JSONArray) JsonEvent.get("seasons"),
                    (JSONArray) JsonEvent.get("choices"));
            events.put(event.getId(), event);
        }
        return events;
    }
}
