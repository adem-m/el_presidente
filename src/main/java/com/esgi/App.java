package com.esgi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Event> eventList = new ArrayList<>();


        JSONHandler jsonHandler = new JSONHandler();
        JSONObject jsonObject = jsonHandler.getObjectFromJSON("events.json");
        JSONArray events = (JSONArray) jsonObject.get("events");
        for (Object event : events) {
            JSONObject eventBis = (JSONObject) event;
            eventList.add(
                    new Event(
                            (long) eventBis.get("id"),
                            (String) eventBis.get("text"),
                            (JSONArray) eventBis.get("seasons"),
                            (JSONArray) eventBis.get("choices")));
        }

    }
}
