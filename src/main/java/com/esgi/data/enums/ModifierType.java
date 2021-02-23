package com.esgi.data.enums;

public enum ModifierType {
    FLAT,
    PERCENTAGE;

    public static ModifierType fromString(String modifierType) {
        switch (modifierType) {
            case "percentage":
                return PERCENTAGE;
            case "flat":
                return FLAT;
        }
        return null;
    }
}
