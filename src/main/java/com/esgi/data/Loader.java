package com.esgi.data;

import com.esgi.utils.JSONHandler;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class Loader {
    private final static String SCENARIOS_FILE_NAME = "scenarios_attributes.json";

    public static Scenario fetchScenarioFromName(String scenarioName) {
        JSONObject jsonObject = new JSONHandler().getObjectFromJSON(SCENARIOS_FILE_NAME);
        JSONObject jsonScenario = (JSONObject) ((JSONObject) jsonObject.get("scenarios")).get(scenarioName);
        return new Scenario(
                scenarioName,
                (Long) jsonScenario.get("industry"),
                (Long) jsonScenario.get("agriculture"),
                (Long) jsonScenario.get("money"),
                (Long) jsonScenario.get("food"),
                (Long) jsonScenario.get("firstEventId"),
                (JSONArray) jsonScenario.get("factions")
        );
    }

    public static Map<Integer, Event> fetchEvents(String scenarioName) {
        Map<Integer, Event> events = new HashMap<>();
        JSONObject jsonObject = new JSONHandler().getObjectFromJSON("events/" + scenarioName + ".json");
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

    public static List<Pair<String, String>> fetchScenariosList() {
        List<Pair<String, String>> scenarios = new ArrayList<>();
        JSONObject jsonObject = new JSONHandler().getObjectFromJSON(SCENARIOS_FILE_NAME);
        JSONObject jsonScenario = (JSONObject) jsonObject.get("scenarios");
        for (Object object : jsonScenario.keySet()) {
            String key = object.toString();
            String value = (String) ((JSONObject) jsonScenario.get(key)).get("name");
            scenarios.add(new Pair<>(key, value));
        }
        return scenarios;
    }
}
