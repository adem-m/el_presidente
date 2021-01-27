package com.esgi;

public enum Season {
    SPRING(0),
    SUMMER(1),
    AUTUMN(2),
    WINTER(3);

    private final int id;

    Season(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
