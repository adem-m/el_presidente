package com.esgi.data.enums;

public enum Season {
    SPRING(0, "Printemps"),
    SUMMER(1, "Eté"),
    AUTUMN(2, "Automne"),
    WINTER(3, "Hiver");

    private final int id;

    private final String name;

    Season(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

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
