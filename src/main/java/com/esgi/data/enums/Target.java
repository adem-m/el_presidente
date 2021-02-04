package com.esgi.data.enums;

public enum Target {
    ATTRIBUTE,
    FACTION;

    public static Target fromString(String target){
        return target.equals("attribute") ? ATTRIBUTE : FACTION;
    }
}
