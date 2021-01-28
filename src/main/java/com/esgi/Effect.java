package com.esgi;

public class Effect {
    private final String type;
    private String factionName;
    private final String attribute;
    private final int modifier;
    private ModifierType modifierType;

    public Effect(String type, String factionName, String attribute, Long modifier, String modifierType) {
        this.type = type;
        this.factionName = factionName;
        this.attribute = attribute;
        this.modifier = modifier.intValue();
        this.modifierType = ModifierType.fromString(modifierType);
    }

    public Effect(String type, String attribute, Long modifier, String modifierType) {
        this.type = type;
        this.attribute = attribute;
        this.modifier = modifier.intValue();
        this.modifierType = ModifierType.fromString(modifierType);
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

    public ModifierType getModifierType() {
        return modifierType;
    }
}
