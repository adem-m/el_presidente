package com.esgi.data;

import com.esgi.data.enums.Difficulty;
import com.esgi.game.ChoiceHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventChoiceTest {
    final static String SCENARIO_NAME = "tsar";

    private final EventChoice eventChoice = Loader.fetchEvents(SCENARIO_NAME).get(1).getChoices().get(0);

    @Test
    public void should_generate_label_easy() {
        Difficulty difficulty = Difficulty.EASY;

        Object[] params = new Object[this.eventChoice.getEffects().size()];
        for (int i = 0; i < params.length; i++) {
            params[i] = ChoiceHandler.applyDifficultyToModifier(
                    this.eventChoice.getEffects().get(i).getModifier(),
                    difficulty,
                    this.eventChoice.getEffects().get(i).getEffectType()
            );
        }
        assertEquals(String.format(this.eventChoice.getText(), params), this.eventChoice.generateLabel(difficulty));
    }

    @Test
    public void should_generate_label_normal() {
        Difficulty difficulty = Difficulty.NORMAL;

        Object[] params = new Object[this.eventChoice.getEffects().size()];
        for (int i = 0; i < params.length; i++) {
            params[i] = ChoiceHandler.applyDifficultyToModifier(
                    this.eventChoice.getEffects().get(i).getModifier(),
                    difficulty,
                    this.eventChoice.getEffects().get(i).getEffectType()
            );
        }
        assertEquals(String.format(this.eventChoice.getText(), params), this.eventChoice.generateLabel(difficulty));
    }

    @Test
    public void should_generate_label_hard() {
        Difficulty difficulty = Difficulty.HARD;

        Object[] params = new Object[this.eventChoice.getEffects().size()];
        for (int i = 0; i < params.length; i++) {
            params[i] = ChoiceHandler.applyDifficultyToModifier(
                    this.eventChoice.getEffects().get(i).getModifier(),
                    difficulty,
                    this.eventChoice.getEffects().get(i).getEffectType()
            );
        }
        assertEquals(String.format(this.eventChoice.getText(), params), this.eventChoice.generateLabel(difficulty));
    }


}