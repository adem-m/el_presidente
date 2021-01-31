package com.esgi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private final String name;
    private final int industry;
    private final int agriculture;
    private final int money;
    private final int firstEventId;
    private final List<Faction> factions = new ArrayList<>();

    public Scenario(String name, Long industry, Long agriculture, Long money, Long firstEventId, JSONArray factions) {
        this.name = name;
        this.industry = industry.intValue();
        this.agriculture = agriculture.intValue();
        this.money = money.intValue();
        this.firstEventId = firstEventId.intValue();
        fillFactions(factions);
    }

    public String getName() {
        return name;
    }

    public int getIndustry() {
        return industry;
    }

    public int getAgriculture() {
        return agriculture;
    }

    public int getMoney() {
        return money;
    }

    public int getFirstEventId() {
        return firstEventId;
    }

    public List<Faction> getFactions() {
        return factions;
    }

    private void fillFactions(JSONArray factions) {
        for (Object object : factions) {
            JSONObject faction = (JSONObject) object;
            this.factions.add(new Faction(
                    (String) faction.get("name"),
                    (Long) faction.get("population"),
                    (Long) faction.get("satisfaction")
            ));
        }
    }
}
