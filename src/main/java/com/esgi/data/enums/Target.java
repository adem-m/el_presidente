package com.esgi.data.enums;

public enum Target {
    ATTRIBUTE,
    FACTION;

    public static Target fromString(String target) {
        switch (target) {
            case "attribute":
                return ATTRIBUTE;
            case "faction":
                return FACTION;
        }
        return null;
    }
}
