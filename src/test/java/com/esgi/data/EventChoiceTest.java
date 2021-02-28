package com.esgi.data;

import com.esgi.data.enums.Difficulty;
import com.esgi.game.ChoiceHandler;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EventChoiceTest {

    @Test
    public void should_generate_label_easy() {
        Map<Integer, Event> events = Loader.fetchEvents("tsar");
        EventChoice eventChoice = events.get(1).getChoices().get(0);

        Effect religiousEffect = eventChoice.getEffects().get(0);
        int religiousModifier =  ChoiceHandler.applyDifficultyToModifier(religiousEffect.getModifier(),Difficulty.EASY,religiousEffect.getEffectType());
        Effect nationalistEffect = eventChoice.getEffects().get(1);
        int nationalistModifier =  ChoiceHandler.applyDifficultyToModifier(nationalistEffect.getModifier(),Difficulty.EASY,nationalistEffect.getEffectType());;

        String label = eventChoice.generateLabel(Difficulty.EASY);

        int posReligiousModifier = label.indexOf("religieux") + 11;
        int extractReligiousModifier = Integer.parseInt(label.substring(posReligiousModifier,posReligiousModifier+2));
        assertEquals(religiousModifier,extractReligiousModifier);

        int posNationalistModifier = label.indexOf("nationalistes") + 15;
        int extractNationalistModifier = Integer.parseInt(label.substring(posNationalistModifier,posNationalistModifier+2));
        assertEquals(nationalistModifier,extractNationalistModifier);

    }
    @Test
    public void should_generate_label_normal() {
        Map<Integer, Event> events = Loader.fetchEvents("tsar");
        EventChoice eventChoice = events.get(1).getChoices().get(0);

        Effect religiousEffect = eventChoice.getEffects().get(0);
        int religiousModifier =  ChoiceHandler.applyDifficultyToModifier(religiousEffect.getModifier(),Difficulty.NORMAL,religiousEffect.getEffectType());
        Effect nationalistEffect = eventChoice.getEffects().get(1);
        int nationalistModifier =  ChoiceHandler.applyDifficultyToModifier(nationalistEffect.getModifier(),Difficulty.NORMAL,nationalistEffect.getEffectType());;

        String label = eventChoice.generateLabel(Difficulty.NORMAL);

        int posReligiousModifier = label.indexOf("religieux") + 11;
        int extractReligiousModifier = Integer.parseInt(label.substring(posReligiousModifier,posReligiousModifier+2));
        assertEquals(religiousModifier,extractReligiousModifier);

        int posNationalistModifier = label.indexOf("nationalistes") + 14;
        int extractNationalistModifier = Integer.parseInt(label.substring(posNationalistModifier,posNationalistModifier+2));
        assertEquals(nationalistModifier,extractNationalistModifier);
    }
    @Test
    public void should_generate_label_hard() {
        Map<Integer, Event> events = Loader.fetchEvents("tsar");
        EventChoice eventChoice = events.get(1).getChoices().get(0);

        Effect religiousEffect = eventChoice.getEffects().get(0);
        int religiousModifier =  ChoiceHandler.applyDifficultyToModifier(religiousEffect.getModifier(),Difficulty.HARD,religiousEffect.getEffectType());
        Effect nationalistEffect = eventChoice.getEffects().get(1);
        int nationalistModifier =  ChoiceHandler.applyDifficultyToModifier(nationalistEffect.getModifier(),Difficulty.HARD,nationalistEffect.getEffectType());;

        String label = eventChoice.generateLabel(Difficulty.HARD);

        int posReligiousModifier = label.indexOf("religieux") + 11;
        int extractReligiousModifier = Integer.parseInt(label.substring(posReligiousModifier,posReligiousModifier+2));
        assertEquals(religiousModifier,extractReligiousModifier);

        int posNationalistModifier = label.indexOf("nationalistes") + 14;
        int extractNationalistModifier = Integer.parseInt(label.substring(posNationalistModifier,posNationalistModifier+2));
        assertEquals(nationalistModifier,extractNationalistModifier);

    }


}