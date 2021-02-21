package com.esgi.data;

import com.esgi.data.enums.Difficulty;
import com.esgi.data.enums.Season;
import com.esgi.game.ChoiceHandler;

import java.util.*;

public class State {
    private final Map<String, Integer> attributes = new HashMap<>();
    private final Map<String, Faction> factions = new HashMap<>();
    private final Map<Integer, Event> events = new HashMap<>();
    private final List<Event> nextEvents = new ArrayList<>();
    private int turnCount = 1;
    private final Season startingSeason;
    private final ChoiceHandler choiceHandler;
    private boolean sandboxMode = false;
    private final Difficulty difficulty;

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public Map<String, Integer> getAttributes() {
        return this.attributes;
    }

    public Map<String, Faction> getFactions() {
        return this.factions;
    }

    public Map<Integer, Event> getEvents() {
        return this.events;
    }

    public List<Event> getNextEvents() {
        return this.nextEvents;
    }

    public int getTurnCount() {
        return this.turnCount;
    }

    public boolean hasYearPassed() {
        return this.turnCount % 4 == 0;
    }

    public Season getStartingSeason() {
        return this.startingSeason;
    }

    public State(String scenarioName, Difficulty difficulty) {
        this.difficulty = difficulty;
        this.choiceHandler = new ChoiceHandler(this);
        this.events.putAll(Loader.fetchEvents(scenarioName));
        Scenario scenario = Loader.fetchScenarioFromName(scenarioName);
        initializeAttributesFromScenario(scenario);
        this.startingSeason = Season.fromId(new Random().nextInt(4));
    }

    private void initializeAttributesFromScenario(Scenario scenario) {
        if (!scenario.getName().equals("sandbox")) {
            try {
                this.nextEvents.add(getEventById(scenario.getFirstEventId()));
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        } else {
            this.sandboxMode = true;
        }
        this.attributes.put("industry", scenario.getIndustry());
        this.attributes.put("agriculture", scenario.getAgriculture());
        this.attributes.put("money", scenario.getMoney());
        this.attributes.put("food", scenario.getFood());
        for (Faction faction : scenario.getFactions()) {
            this.factions.put(faction.getName(), faction);
        }
    }

    public Event getNextEvent() {
        if (this.sandboxMode) {
            return getRandomEvent();
        }
        if (!this.nextEvents.isEmpty()) {
            Event event = this.nextEvents.get(0);
            this.nextEvents.remove(0);
            return event;
        }
        turnSandboxMode();
        return getRandomEvent();
    }

    private void turnSandboxMode() {
        this.sandboxMode = true;
        this.events.clear();
        this.events.putAll(Loader.fetchEvents("sandbox"));
    }

    public Event getEventById(int id) {
        return this.events.get(id);
    }

    private Event getRandomEvent() {
        Random generator = new Random();
        Object[] values = this.events.values().toArray();
        Event event = (Event) values[generator.nextInt(values.length)];
        if (!event.getSeasons().contains(getCurrentSeason())) {
            return getRandomEvent();
        }
        return event;
    }

    public void handleChoice(EventChoice choice) {
        this.choiceHandler.handle(choice, this.difficulty);
        this.turnCount++;
    }

    public void handleYearlyChoice(EventChoice choice) {
        this.choiceHandler.handle(choice, this.difficulty);
    }

    public void handleYearlyResultsChoice(EventChoice choice) {
        this.choiceHandler.handle(choice, this.difficulty);
    }

    public Season getCurrentSeason() {
        return Season.fromId((this.turnCount - 1 + this.startingSeason.getId()) % 4);
    }

    public int calculateGlobalSatisfaction() {
        int satisfaction = 0;
        int population = 0;
        for (Map.Entry<String, Faction> faction : this.factions.entrySet()) {
            satisfaction += faction.getValue().getSatisfaction() * faction.getValue().getPopulation();
            population += faction.getValue().getPopulation();
        }
        if (population == 0) {
            return 0;
        }
        return satisfaction / population;
    }

    public int calculateTotalPopulation() {
        int population = 0;
        for (Map.Entry<String, Faction> faction : this.factions.entrySet()) {
            population += faction.getValue().getPopulation();
        }
        return population;
    }

    public boolean isGameLost() {
        return calculateGlobalSatisfaction() < this.difficulty.getMinimumGlobalSatisfaction() ||
                calculateTotalPopulation() == 0;
    }

    public Faction getRandomFaction() {
        List<String> factionKeys = new ArrayList<>(this.factions.keySet());
        return this.factions.get(factionKeys.get(new Random().nextInt(factionKeys.size())));
    }

    public String generateStateDisplay() {
        return new DisplayGenerator(this).generateStateDisplay();
    }
}
