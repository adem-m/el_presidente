package com.esgi.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scenario implements Serializable {
    private final String name;
    private final int industry;
    private final int agriculture;
    private final int money;
    private final int food;
    private final int firstEventId;
    private final List<Faction> factions = new ArrayList<>();

    public Scenario(String name, Long industry, Long agriculture, Long money, Long food, Long firstEventId, JSONArray factions) {
        this.name = name;
        this.industry = industry.intValue();
        this.agriculture = agriculture.intValue();
        this.money = money.intValue();
        this.food = food.intValue();
        this.firstEventId = firstEventId.intValue();
        fillFactions(factions);
    }

    public String getName() {
        return this.name;
    }

    public int getIndustry() {
        return this.industry;
    }

    public int getAgriculture() {
        return this.agriculture;
    }

    public int getMoney() {
        return this.money;
    }

    public int getFood() {
        return this.food;
    }

    public int getFirstEventId() {
        return this.firstEventId;
    }

    public List<Faction> getFactions() {
        return this.factions;
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
