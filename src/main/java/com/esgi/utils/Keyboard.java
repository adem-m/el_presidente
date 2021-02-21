package com.esgi.utils;

import java.util.Scanner;

public class Keyboard {
    private Scanner scanner;
    
    public Keyboard(){
        this.scanner = new Scanner( System.in );
    }

    public void expectEnter(){
        this.scanner.nextLine();
    }
    
    public int getUserInput(){
        System.out.print( "señor presidente> ");
        return Integer.parseInt( this.scanner.nextLine());
    }
}
