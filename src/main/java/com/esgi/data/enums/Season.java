package com.esgi.data.enums;

public enum Season {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER;

    public static Season fromId(int id) {
        switch (id) {
            case 0:
                return SPRING;
            case 1:
                return SUMMER;
            case 2:
                return AUTUMN;
            case 3:
                return WINTER;
        }
        return null;
    }
}
