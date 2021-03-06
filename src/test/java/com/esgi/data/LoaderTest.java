package com.esgi.data;

import com.esgi.utils.Pair;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoaderTest {


    @Test
    public void should_fetch_Scenario_From_Name() {
        Scenario scenario = Loader.fetchScenarioFromName("sandbox");
        assertEquals(15,scenario.getAgriculture());
        assertEquals(-1,scenario.getFirstEventId());
        assertEquals(15,scenario.getIndustry());
        assertEquals("sandbox",scenario.getName());
        assertEquals(200,scenario.getMoney());
    }


    @Test
    public void should_get_sandBoxScenario_given_scenariosList() {
        List<Pair> scenarioList = Loader.fetchScenariosList();
        assertEquals("tsar",scenarioList.get(0).getKey());
        assertEquals("sandbox",scenarioList.get(1).getKey());
        assertEquals("fma",scenarioList.get(2).getKey());
        assertEquals("cold_war",scenarioList.get(3).getKey());
    }
}