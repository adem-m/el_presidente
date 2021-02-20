package com.esgi.data;

import com.esgi.data.enums.Difficulty;
import com.esgi.game.ChoiceHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventChoice {
    private final String text;
    private final List<Effect> effects = new ArrayList<>();
    private final List<Integer> nextEventsIds = new ArrayList<>();

    public EventChoice(String text, JSONArray nextEventsIds, JSONArray effects) {
        this.text = text;
        for (Object eventId : nextEventsIds) {
            Long id = (Long) eventId;
            this.nextEventsIds.add(id.intValue());
        }
        for (Object effect : effects) {
            this.effects.add(JSONtoEffect((JSONObject) effect));
        }
    }

    public EventChoice(String text, List<Effect> effects) {
        this.text = text;
        this.effects.addAll(effects);
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public List<Integer> getNextEventsIds() {
        return nextEventsIds;
    }

    public String generateLabel(Difficulty difficulty) {
        Object[] values = new Object[effects.size()];
        for (int i = 0; i < effects.size(); i++) {
            values[i] = ChoiceHandler.applyDifficultyToModifier(
                    effects.get(i).getModifier(),
                    difficulty,
                    effects.get(i).getEffectType()
            );
        }
        return String.format(this.text, values);
    }

    private Effect JSONtoEffect(JSONObject JSONEffect) {
        if (JSONEffect.get("target").toString().equals("faction")) {
            return new Effect(
                    (String) JSONEffect.get("target"),
                    (String) JSONEffect.get("factionName"),
                    (String) JSONEffect.get("attribute"),
                    (Long) JSONEffect.get("modifier"),
                    (String) JSONEffect.get("modifierType"),
                    (String) JSONEffect.get("effectType"));
        } else {
            return new Effect(
                    (String) JSONEffect.get("target"),
                    (String) JSONEffect.get("attribute"),
                    (Long) JSONEffect.get("modifier"),
                    (String) JSONEffect.get("modifierType"),
                    (String) JSONEffect.get("effectType"));
        }
    }
}
