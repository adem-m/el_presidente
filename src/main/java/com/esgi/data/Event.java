package com.esgi.data;

import com.esgi.data.enums.Season;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private final int id;
    private final String text;
    private final List<Season> seasons = new ArrayList<>();
    private final List<EventChoice> choices = new ArrayList<>();

    public Event(long id, String text, JSONArray seasonIds, JSONArray choices) {
        this.id = (int) id;
        this.text = text;
        for (Object seasonId : seasonIds) {
            this.seasons.add(getSeasonFromSeasonId((long) seasonId));
        }
        for (Object choice : choices) {
            this.choices.add(JSONtoEventChoice((JSONObject) choice));
        }
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public List<EventChoice> getChoices() {
        return choices;
    }

    private Season getSeasonFromSeasonId(long seasonId) {
        return Season.fromId((int) seasonId);
    }

    private EventChoice JSONtoEventChoice(JSONObject JSONChoice) {
        return new EventChoice(
                (String) JSONChoice.get("text"),
                (JSONArray) JSONChoice.get("nextEvents"),
                (JSONArray) JSONChoice.get("effects"));
    }
}
