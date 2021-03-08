
# El Presidente

El presidente is a video game between Reigns and Tropico.

## Installation

To play the game, please enter the following into your terminal. (we recommend you to use Windows PowerShell to see correctly the special characters)
```bash
java -jar target/el_presidente-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Architecture

We decided to use JSON files to store all the game variables like attributes and scenario events. We also to divide the game in two big parts : 

 - The game state
 - The game loop
 
 The source code is compliant with the clean code guidelines. There was several refactoring session to be sure that the code is still clear and maintenable.

 ### Game Loop

 The State Pattern is used to switch between the different screens: there is a Screen abstract class that implements a "handleInput" method in order to do something according to a user command. That method is called in an infinite loop in the App class. In this case, the context is the App class, and the State is the Screen class. A stack is used to save the previous screens when navigating, so that it is always possible to go back to a previous screen (depending on the current screen).

 ### Game creation

 While navigating between the different screens, the user will be asked to choose a difficulty, a scenario (from which the different events will be chosen) and a mode. All these attributes are needed to build a State instance for the game. A Builder Pattern is used to collect and save the different properties before building a State instance when the user reaches the PlayScreen.

 ### Gameplay

 The user will have to make different choices in a game, some might lead to their downfall ! At the moment, two modes are implemented: the scenario mode and the sandbox mode. Both will need a theme (for instance Tsar or FMA), the difference lies in the selection of the events. In Scenario Mode, the events have a specific order and when the user reaches the last one, the game is won and the user is sent back to the MainTitleScreen. In Sanbox Mode however, the events are randomly pulled from the event pool, only an event compatible with the current season can be picked.

### Save

 All the information that defines a game is centralized in the State object which is created at the beginning. So, to save the user's progression, we just have to save the content of the State object into a file and to load it if needed. To do this, we used serialization to handle the "object to file" conversion.

## Collaborators

Paolo MANAOIS  
Ichaï CHTITSKI  
Adem MRIZAK

## Notes

This project was partially realized with the help of ***Design Patterns*** - 
*Apprendre la conception de logiciels en réalisant un jeu vidéo (avec exercices et corrigés)* 
by Philippe GOSSELIN, published by eni
