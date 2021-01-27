package com.esgi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {
    private final Map<String, Integer> attributesMap = new HashMap<>();
    private final Map<String, Faction> factionMap = new HashMap<>();
    private final List<Event> events = new ArrayList<>();
    private final List<Event> nextEvents = new ArrayList<>();
    private int turnCount;

}
