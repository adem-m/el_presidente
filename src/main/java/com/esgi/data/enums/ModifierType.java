package com.esgi.data.enums;

public enum ModifierType {
    FLAT,
    PERCENTAGE;

    public static ModifierType fromString(String modifierType) {
        if (modifierType.equals("percentage")) {
            return PERCENTAGE;
        } else {
            return FLAT;
        }
    }
}
