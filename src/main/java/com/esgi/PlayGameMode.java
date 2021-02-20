package com.esgi;

import com.esgi.builders.StateBuilder;
import com.esgi.data.State;

public class PlayGameMode extends GameMode
{
    private State state;

    public PlayGameMode( StateBuilder statebuilder )
    {
        this.state = statebuilder.build();
    }

    @Override
    void init()
    {
        System.out.println( "Game initiated" );
    }

    @Override
    void handleInput()
    {

    }
}
