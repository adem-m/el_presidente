package com.esgi.utils;

import java.util.Scanner;

public class Keyboard {
    private Scanner scanner;
    
    public Keyboard(){
        this.scanner = new Scanner( System.in );
    }
    
    public char getUserInput(){
        String input = this.scanner.nextLine();
        return input.isEmpty() ? 13 : input.charAt( 0 );
    }
}
