package com.esgi.data;

import com.esgi.data.enums.EffectType;
import com.esgi.data.enums.ModifierType;
import com.esgi.data.enums.Target;

public class Effect {
    private final Target target;
    private String factionName;
    private final String attribute;
    private int modifier;
    private final ModifierType modifierType;
    private final EffectType effectType;

    public Effect(String target, String factionName, String attribute, Long modifier, String modifierType, String effectType) {
        this.target = Target.fromString(target);
        this.factionName = factionName;
        this.attribute = attribute;
        this.modifier = modifier.intValue();
        this.modifierType = ModifierType.fromString(modifierType);
        this.effectType = EffectType.fromString(effectType);
    }

    public Effect(String target, String attribute, Long modifier, String modifierType, String effectType) {
        this.target = Target.fromString(target);
        this.attribute = attribute;
        this.modifier = modifier.intValue();
        this.modifierType = ModifierType.fromString(modifierType);
        this.effectType = EffectType.fromString(effectType);
    }

    public Target getTarget() {
        return target;
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

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public ModifierType getModifierType() {
        return modifierType;
    }

    public EffectType getEffectType() {
        return effectType;
    }
}
