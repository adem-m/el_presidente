package com.esgi.data.enums;

public enum EffectType {
    BONUS,
    MALUS;

    public static EffectType fromString(String label) {
        switch (label) {
            case "bonus":
                return BONUS;
            case "malus":
                return MALUS;
        }
        return null;
    }
}
