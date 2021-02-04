package com.esgi.game;

import com.esgi.data.Effect;
import com.esgi.data.EventChoice;
import com.esgi.data.Faction;
import com.esgi.data.State;
import com.esgi.data.enums.Target;

public class ChoiceHandler {
    private final State state;

    public ChoiceHandler(State state) {
        this.state = state;
    }

    public void handle(EventChoice choice) {
        for (Integer i : choice.getNextEventsIds()) {
            try {
                this.state.getNextEvents().add(this.state.getEventById(i));
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        for (Effect effect : choice.getEffects()) {
            try {
                executeEffect(effect);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void executeEffect(Effect effect) throws Exception {
        if (effect.getTarget() == Target.FACTION) {
            executeFactionEffect(effect);
        } else if (effect.getTarget() == Target.ATTRIBUTE) {
            executeAttributeEffect(effect);
        } else {
            throw new Exception("Unknown effect type : " + effect.getTarget());
        }
    }

    private void executeAttributeEffect(Effect effect) {
        if (effect.getAttribute().equals("money")) {
            this.state.getAttributes().put("money", this.state.getAttributes().get("money") + effect.getModifier());
            if (this.state.getAttributes().get("money") < 0) {
                this.state.getAttributes().put("money", 0);
            }
        } else {
            modifyAgricultureOrIndustry(effect.getAttribute(), effect.getModifier());
        }
    }

    private void executeFactionEffect(Effect effect) {
        Faction faction = this.state.getFactions().get(effect.getFactionName());
        if (effect.getAttribute().equals("satisfaction")) {
            faction.modifySatisfaction(effect.getModifier());
        } else {
            faction.modifyPopulation(effect.getModifier());
        }
    }

    private void modifyAgricultureOrIndustry(String attribute, int modifier) {
        String other = attribute.equals("industry") ? "agriculture" : "industry";
        int currentValue = this.state.getAttributes().get(attribute);
        int modification = calculateModificationValue(currentValue, modifier);
        this.state.getAttributes().put(attribute, currentValue + modification);
        int total = this.state.getAttributes().get(attribute) + this.state.getAttributes().get(other);
        if (total > 100) {
            this.state.getAttributes().put(other, this.state.getAttributes().get(other) - (total - 100));
        }
    }

    private int calculateModificationValue(int currentValue, int modifier) {
        if (currentValue + modifier > 100) {
            return currentValue + modifier - 100;
        } else if (currentValue + modifier < 0) {
            return currentValue * (-1);
        } else {
            return modifier;
        }
    }
}
