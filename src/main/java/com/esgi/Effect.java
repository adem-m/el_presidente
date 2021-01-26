package com.esgi;

public class Effect {
    private final String factionName;
    private final String attribute;
    private final int modifier;

    public Effect(String factionName, String attribute, int modifier) {
        this.factionName = factionName;
        this.attribute = attribute;
        this.modifier = modifier;
    }

    public String getFactionName() {
        return factionName;
    }

    public String getAttribute() {
        return attribute;
    }

    public int getModifier() {
        return modifier;
    }
}
