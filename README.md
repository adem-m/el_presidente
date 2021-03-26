
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

### Collaborators
Paolo MANAOIS  
Ichaï CHTITSKI  
Adem MRIZAK
