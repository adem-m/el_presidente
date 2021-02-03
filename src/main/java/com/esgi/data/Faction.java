package com.esgi.data;

public class Faction {
    private final String name;
    private int population;
    private int satisfaction;

    public Faction(String name, Long population, Long satisfaction) {
        this.name = name;
        this.population = population.intValue();
        this.satisfaction = satisfaction.intValue();
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void modifyPopulation(int modifier) {
        this.population += modifier;
        if (this.population < 0) {
            population = 0;
        }
    }

    public void modifySatisfaction(int modifier) {
        if (this.satisfaction > 0) {
            this.satisfaction += modifier;
            if (this.satisfaction < 0) {
                this.satisfaction = 0;
            }
            if (this.satisfaction > 100) {
                this.satisfaction = 100;
            }
        }
    }

}
