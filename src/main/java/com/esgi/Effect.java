package com.esgi;

public class Effect {
    private final String type;
    private String factionName;
    private final String attribute;
    private final int modifier;

    public Effect(String type, String factionName, String attribute, Long modifier) {
        this.type = type;
        this.factionName = factionName;
        this.attribute = attribute;
        this.modifier = modifier.intValue();
    }

    public Effect(String type, String attribute, Long modifier) {
        this.type = type;
        this.attribute = attribute;
        this.modifier = modifier.intValue();
    }

    public String getType() {
        return type;
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
