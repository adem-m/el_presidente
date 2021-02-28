package com.esgi.data;

import java.io.Serializable;

public class Faction implements Comparable<Faction>, Serializable {
    final static int MINIMUM_SATISFACTION = 0;
    final static int MAXIMUM_SATISFACTION = 100;
    final static int MINIMUM_POPULATION = 0;

    private final String name;
    private int population;
    private int satisfaction;

    public Faction(String name, Long population, Long satisfaction) {
        this.name = name;
        this.population = population.intValue();
        this.satisfaction = satisfaction.intValue();
    }

    public String getName() {
        return this.name;
    }

    public int getPopulation() {
        return this.population;
    }

    public int getSatisfaction() {
        return this.satisfaction;
    }

    public void modifyPopulation(int modifier) {
        this.population += modifier;
        if (this.population < MINIMUM_POPULATION) {
            this.population = MINIMUM_POPULATION;
        }
    }

    public void modifySatisfaction(int modifier) {
        if (this.satisfaction > MINIMUM_SATISFACTION) {
            this.satisfaction += modifier;
            if (this.satisfaction < MINIMUM_SATISFACTION) {
                this.satisfaction = MINIMUM_SATISFACTION;
            }
            if (this.satisfaction > MAXIMUM_SATISFACTION) {
                this.satisfaction = MAXIMUM_SATISFACTION;
            }
        }
    }

    @Override
    public int compareTo(Faction o) {
        return Integer.compare(
                o.getPopulation() * o.getSatisfaction(),
                this.getPopulation() * this.getSatisfaction()
        );
    }
}
