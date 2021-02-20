package com.esgi;
import com.esgi.builders.StateBuilder;
import com.esgi.data.Loader;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ScenarioGameMode extends GameMode
{
    private List<String> choices = new ArrayList<String>();

    @Override
    void init() {
        int index = 0;
        for( Pair<String, String> scenario: Loader.fetchScenariosList() )
        {
            System.out.println( String.format( "%d - %s", ++index, scenario.getValue() ));
            this.choices.add( scenario.getKey() );
        }
        this.choices.add( "exit" );
        System.out.println( String.format( "%d - %s", ++index, "exit" ));
    }

    @Override
    void handleInput() {
        int input = this.inputHandler.getUserInput();
        String value = this.choices.get( input - 1 );
        if( value.equals( "exit" ))
        {
            System.out.println( "Fermeture..." );
            System.exit( 0 );
        } else
        {
            this.switchGameMode(
                    new DifficultyGameMode(
                            new StateBuilder().setScenarioName( this.choices.get( input - 1 ))));
        }
    }
}
