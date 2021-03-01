package com.esgi.modes;

import java.util.PriorityQueue;
import java.util.Queue;

import com.esgi.data.Event;
import com.esgi.data.Scenario;
import com.esgi.data.enums.Difficulty;

public class ScenarioState extends State {
    private final Queue<Event> nextEvents = new PriorityQueue<Event>();

    public ScenarioState( String scenarioName, Difficulty difficulty ){
        super( scenarioName, difficulty );
    }

    @Override
    Event getNextEvent(){
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
