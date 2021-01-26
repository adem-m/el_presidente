package com.esgi;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private final int id;
    private final String text;
    private final List<Season> seasons = new ArrayList<>();
    private final List<EventChoice> choices = new ArrayList<>();

    public Event(int id, String text) {
        this.id = id;
        this.text = text;
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
}
