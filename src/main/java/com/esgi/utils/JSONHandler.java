package com.esgi.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class JSONHandler {
    public JSONObject getObjectFromJSON(String fileName) {
        JSONParser jsonParser = new JSONParser();
        Object json = null;
        try {
            json = jsonParser.parse(getResourceFromName(fileName));
        } catch (Exception exception) {
            System.out.println("Error : " + exception.getMessage());
        }
        return (JSONObject) json;
    }

    private String getResourceFromName(String fileName) throws Exception {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream != null) {
            return new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
        }
        throw new Exception("Unable to fetch " + fileName);
    }
}
