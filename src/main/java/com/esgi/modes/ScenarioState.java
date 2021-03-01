package com.esgi.modes;

import com.esgi.data.Event;
import com.esgi.data.Scenario;
import com.esgi.data.enums.Difficulty;

public class ScenarioState extends State {
    public ScenarioState( String scenarioName, Difficulty difficulty ){
        super( scenarioName, difficulty );
    }

    @Override
    public Event getNextEvent() {
        return this.nextEvents.poll();
    }

    @Override
    protected void initializeAttributesFromScenario( Scenario scenario ){
        try {
            this.nextEvents.add( getEventById( scenario.getFirstEventId() ));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        super.initializeAttributesFromScenario( scenario );
    }
    
}
