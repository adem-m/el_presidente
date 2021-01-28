package com.esgi;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONHandler {
    public JSONObject getObjectFromJSON(String fileName) {
        JSONParser jsonParser = new JSONParser();

        Object json = null;
        try {
            json = jsonParser.parse(
                    new FileReader(
                            new File("")
                                    .getAbsolutePath()
                                    .concat("\\src\\main\\resources\\" + fileName)));
        } catch (IOException | ParseException e) {
            System.out.println("Error while fetching object from " + fileName);
        }
        return (JSONObject) json;
    }

}
