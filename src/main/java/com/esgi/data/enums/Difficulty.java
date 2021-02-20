package com.esgi.data.enums;

public enum Difficulty {
    EASY(1, 1, 10),
    NORMAL(2, 1.2f, 25),
    HARD(3, 1.5f, 50);

    private final int id;
    private final float multiplier;
    private final int minimumGlobalSatisfaction;

    Difficulty(int id, float multiplier, int minimumGlobalSatisfaction) {
        this.id = id;
        this.multiplier = multiplier;
        this.minimumGlobalSatisfaction = minimumGlobalSatisfaction;
    }

    public float getMultiplier() {
        return this.multiplier;
    }

    public int getMinimumGlobalSatisfaction() {
        return this.minimumGlobalSatisfaction;
    }

    static public Difficulty fromId(int id) {
        switch (id) {
            case 1:
                return EASY;
            case 2:
                return NORMAL;
            case 3:
                return HARD;
        }

        return null;
    }
}
