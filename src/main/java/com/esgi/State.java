package com.esgi;

import java.util.HashMap;
import java.util.Map;

public class State {
    private final Map<String, Integer> attributesMap = new HashMap<>();
    private final Map<String, Faction> factionMap = new HashMap<>();
    private int turnCount;
}
