package com.esgi.utils;

import com.esgi.modes.State;

import java.io.*;

public class ProgressionSaver {
    static final String FILE_NAME = "progression.save";

    public static void save(State state) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(state);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Progression sauvegardée !");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static boolean isFileFound(){
        File saveFile = new File( FILE_NAME );
        return saveFile.exists() && !saveFile.isDirectory();
    }

    public static State load() {
        FileInputStream fileInputStream;
        State state = null;
        try {
            fileInputStream = new FileInputStream(FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            state = (State) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("Progression chargée !");
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return state;
    }
}
