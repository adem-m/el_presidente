package com.esgi;

public class MainTitleGameMode extends GameMode {
    @Override
    void init(){
        System.out.println( "\n\n¡ Bienvenido en El Presidente !\n\nAppuyez sur 'Entrée' pour continuer'..." );
    }

    @Override
    void handleInput() {
        switch( this.inputHandler.getUserInput() ){
            case 13:
                System.out.println( "Touche pour continuer activée." );
            
            default:
                System.out.println( "Fermeture de l'application..." );
                System.exit( 0 );
        }
    }
}
