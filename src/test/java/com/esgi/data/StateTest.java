package com.esgi.data;

import com.esgi.data.enums.Difficulty;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class StateTest {

    final static String SCENARIO_NAME = "tsar";
    State state = new State("sandbox", Difficulty.NORMAL);

    @Test
    public void hasYearPassed() {
        EventChoice eventChoice = Loader.fetchEvents(SCENARIO_NAME).get(1).getChoices().get(0);
      int i = 0;
        while(i < 4){
            state.handleChoice(eventChoice);
            i++;
        }
        assertTrue(state.hasYearPassed());
    }

    @Test
    public void should_calculate_inital_GlobalSatisfaction() {
       int globalSatisfaction = state.calculateGlobalSatisfaction();
       assertEquals(56,globalSatisfaction);
    }

    @Test
    public void should_calculate_initial_total_population() {
        int totalPopulation = state.calculateTotalPopulation();
        assertEquals(120,totalPopulation);
    }

    @Test
    public void isGameLost() {
        Map<String,Faction> factions = state.getFactions();
        for (Map.Entry<String,Faction> faction : factions.entrySet()){
            faction.getValue().modifySatisfaction(-100);
        }
       assertTrue(state.isGameLost());
    }
}