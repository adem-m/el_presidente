package com.esgi.data;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class FactionTest {
    Scenario scenario = Loader.fetchScenarioFromName("sandbox");
    @Test
    public void should_add_5_capitalist_population_given_sandbox_scenario() {

        Faction faction_capitalist = scenario.getFactions().get(0);
        faction_capitalist.modifyPopulation(5);
        assertEquals(20,faction_capitalist.getPopulation());
    }

    @Test
    public void should_remove_5_capitalist_population_given_sandbox_scenario() {

        Faction faction_capitalist = scenario.getFactions().get(0);
        faction_capitalist.modifyPopulation(-5);
        assertEquals(10,faction_capitalist.getPopulation());
    }
    @Test
    public void should_set_capitalist_population_to_0_after_removing_more_than_actual_population() {

        Faction faction_capitalist = scenario.getFactions().get(0);
        faction_capitalist.modifyPopulation(-50);
        assertEquals(0,faction_capitalist.getPopulation());
    }
    @Test
    public void should_set_capitalist_satisfaction_to_0_after_removing_more_than_actual_satisfaction() {

        Faction faction_capitalist = scenario.getFactions().get(0);
        faction_capitalist.modifySatisfaction(-150);
        assertEquals(0,faction_capitalist.getSatisfaction());
    }
    @Test
    public void should_set_capitalist_satisfaction_to_100_after_adding_more_than_max_satisfaction() {

        Faction faction_capitalist = scenario.getFactions().get(0);
        faction_capitalist.modifySatisfaction(150);
        assertEquals(100,faction_capitalist.getSatisfaction());
    }

}