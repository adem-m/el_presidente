package com.esgi.data;

import com.esgi.data.enums.Difficulty;
import com.esgi.game.ChoiceHandler;
import org.junit.Test;

import java.util.Map;

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
        Effect religiousEffect = eventChoice.getEffects().get(0);
        int religiousModifier = ChoiceHandler.applyDifficultyToModifier(religiousEffect.getModifier(), Difficulty.NORMAL, religiousEffect.getEffectType());
        Effect nationalistEffect = eventChoice.getEffects().get(1);
        int nationalistModifier = ChoiceHandler.applyDifficultyToModifier(nationalistEffect.getModifier(), Difficulty.NORMAL, nationalistEffect.getEffectType());

        String label = eventChoice.generateLabel(Difficulty.NORMAL);

        int posReligiousModifier = label.indexOf("religieux") + 11;
        int extractReligiousModifier = Integer.parseInt(label.substring(posReligiousModifier, posReligiousModifier + 2));
        assertEquals(religiousModifier, extractReligiousModifier);

        int posNationalistModifier = label.indexOf("nationalistes") + 14;
        int extractNationalistModifier = Integer.parseInt(label.substring(posNationalistModifier, posNationalistModifier + 2));
        assertEquals(nationalistModifier, extractNationalistModifier);
    }

    @Test
    public void should_generate_label_hard() {
        Effect religiousEffect = eventChoice.getEffects().get(0);
        int religiousModifier = ChoiceHandler.applyDifficultyToModifier(religiousEffect.getModifier(), Difficulty.HARD, religiousEffect.getEffectType());
        Effect nationalistEffect = eventChoice.getEffects().get(1);
        int nationalistModifier = ChoiceHandler.applyDifficultyToModifier(nationalistEffect.getModifier(), Difficulty.HARD, nationalistEffect.getEffectType());

        String label = eventChoice.generateLabel(Difficulty.HARD);

        int posReligiousModifier = label.indexOf("religieux") + 11;
        int extractReligiousModifier = Integer.parseInt(label.substring(posReligiousModifier, posReligiousModifier + 2));
        assertEquals(religiousModifier, extractReligiousModifier);

        int posNationalistModifier = label.indexOf("nationalistes") + 14;
        int extractNationalistModifier = Integer.parseInt(label.substring(posNationalistModifier, posNationalistModifier + 2));
        assertEquals(nationalistModifier, extractNationalistModifier);

    }


}