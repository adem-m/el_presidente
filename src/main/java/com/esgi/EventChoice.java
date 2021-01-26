package com.esgi;

import java.util.ArrayList;
import java.util.List;

public class EventChoice {
    private final String text;
    private final List<Effect> effects = new ArrayList<>();

    public EventChoice(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public List<Effect> getEffects() {
        return effects;
    }
}
