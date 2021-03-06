package com.esgi.game;

import com.esgi.data.Effect;
import com.esgi.data.EventChoice;
import com.esgi.data.Faction;
import com.esgi.modes.State;
import com.esgi.data.enums.Difficulty;
import com.esgi.data.enums.EffectType;
import com.esgi.data.enums.ModifierType;
import com.esgi.data.enums.Target;

import java.io.Serializable;

public class ChoiceHandler implements Serializable {
    private static final long serialVersionUID = 12345678910111213L;

    final static int MINIMUM_ATTRIBUTE_VALUE = 0;
    final static int MAXIMUM_SUM_OF_AGRICULTURE_AND_INDUSTRY = 100;
    final static int AGRICULTURE_OR_INDUSTRY_MAXIMUM_VALUE = 100;

    private final State state;

    public ChoiceHandler(State state) {
        this.state = state;
    }

    public void handle(EventChoice choice) {
        this.internalHandle(choice);
        this.state.incrementTurnCount();
    }

    public void handleYearlyChoice(EventChoice choice) {
        if (isCorruptionChoice(choice) && !isCorruptionPossible(choice)) {
            System.out.println("Corruption impossible");
            return;
        }
        this.internalHandle(choice);
    }

    private boolean isCorruptionChoice(EventChoice choice) {
        for (Effect effect : choice.getEffects()) {
            if (effect.getTarget() == Target.FACTION &&
                    effect.getAttribute().equals("satisfaction") &&
                    effect.getEffectType() == EffectType.BONUS) {
                return true;
            }
        }
        return false;
    }

    private boolean isCorruptionPossible(EventChoice choice) {
        for (Effect effect : choice.getEffects()) {
            if (effect.getAttribute().equals("satisfaction") && effect.getEffectType() == EffectType.BONUS) {
                if (this.state.getFactions().get(effect.getFactionName()).getSatisfaction() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void internalHandle(EventChoice choice) {
        for (Integer id : choice.getNextEventsIds()) {
            this.state.getNextEvents().offer(this.state.getEventById(id));
        }

        for (Effect effect : choice.getEffects()) {
            if (effect.getAttribute().equals("money")) {
                int modifier = effect.getModifierType() == ModifierType.PERCENTAGE ?
                        calculatePercentage(state.getAttributes().get(effect.getAttribute()), effect.getModifier()) :
                        effect.getModifier();
                if (modifier < 0 && (modifier * -1) > this.state.getAttributes().get("money")) {
                    System.out.println("Pas assez d'argent pour cela.");
                    return;
                }
            }
        }

        for (Effect effect : choice.getEffects()) {
            try {
                executeEffect(effect, this.state.getDifficulty());
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void executeEffect(Effect effect, Difficulty difficulty) throws Exception {
        int modifier = effect.getModifierType() == ModifierType.PERCENTAGE ?
                calculatePercentage(state.getAttributes().get(effect.getAttribute()), effect.getModifier()) :
                effect.getModifier();
        modifier = applyDifficultyToModifier(modifier, difficulty, effect.getEffectType());
        if (effect.getTarget() == Target.FACTION) {
            executeFactionEffect(effect, modifier);
        } else if (effect.getTarget() == Target.ATTRIBUTE) {
            executeAttributeEffect(effect, modifier);
        } else {
            throw new Exception("Unknown effect type : " + effect.getTarget());
        }
    }

    private void executeAttributeEffect(Effect effect, int modifier) {
        String attribute = effect.getAttribute();
        if (attribute.equals("money") || attribute.equals("food")) {
            this.state.getAttributes().put(attribute, this.state.getAttributes().get(attribute) + modifier);
            if (this.state.getAttributes().get(attribute) < MINIMUM_ATTRIBUTE_VALUE) {
                this.state.getAttributes().put(attribute, MINIMUM_ATTRIBUTE_VALUE);
            }
        } else {
            modifyAgricultureOrIndustry(effect.getAttribute(), modifier);
        }
    }

    private void executeFactionEffect(Effect effect, int modifier) {
        Faction faction = this.state.getFactions().get(effect.getFactionName());
        if (effect.getAttribute().equals("satisfaction")) {
            faction.modifySatisfaction(modifier);
        } else {
            faction.modifyPopulation(modifier);
        }
    }

    private void modifyAgricultureOrIndustry(String attribute, int modifier) {
        String other = attribute.equals("industry") ? "agriculture" : "industry";
        int currentValue = this.state.getAttributes().get(attribute);
        int modification = calculateModificationValue(currentValue, modifier);
        this.state.getAttributes().put(attribute, currentValue + modification);
        int total = this.state.getAttributes().get(attribute) + this.state.getAttributes().get(other);
        if (total > MAXIMUM_SUM_OF_AGRICULTURE_AND_INDUSTRY) {
            this.state.getAttributes().put(
                    other,
                    this.state.getAttributes().get(other) - (total - MAXIMUM_SUM_OF_AGRICULTURE_AND_INDUSTRY)
            );
        }
    }

    private int calculateModificationValue(int currentValue, int modifier) {
        if (currentValue + modifier > AGRICULTURE_OR_INDUSTRY_MAXIMUM_VALUE) {
            return AGRICULTURE_OR_INDUSTRY_MAXIMUM_VALUE - currentValue;
        } else if (currentValue + modifier < 0) {
            return currentValue * (-1);
        } else {
            return modifier;
        }
    }

    private int calculatePercentage(int amount, int percentage) {
        return Math.round((amount / 100f) * percentage);
    }

    public static int applyDifficultyToModifier(int modifier, Difficulty difficulty, EffectType effectType) {
        return effectType == EffectType.BONUS ?
                Math.round(modifier / difficulty.getMultiplier()) :
                Math.round(modifier * difficulty.getMultiplier());
    }
}
