package com.esgi.game;

import com.esgi.data.EventChoice;
import com.esgi.data.Loader;
import com.esgi.data.State;
import com.esgi.data.enums.Difficulty;
import com.esgi.data.enums.EffectType;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ChoiceHandlerTest {
    final static String SCENARIO_NAME = "tsar";
    State state = new State("sandbox", Difficulty.NORMAL);
    private final EventChoice eventChoiceMalus = Loader.fetchEvents(SCENARIO_NAME).get(3).getChoices().get(1);
    private final EventChoice eventChoiceBonus = Loader.fetchEvents(SCENARIO_NAME).get(3).getChoices().get(0);
    @Test
    public void handle_should_print_error_message_given_not_enough_money() {
     // prepare l'output a tester
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOutput = "Pas assez d'argent pour cela.\r\n";

        state.getAttributes().put("money",0);
        ChoiceHandler choiceHandler = new ChoiceHandler(state);
        choiceHandler.handle(eventChoiceMalus,Difficulty.NORMAL);

        assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    public void should_apply_difficulty_to_modifier_easy_malus() {
      int modifier = eventChoiceMalus.getEffects().get(0).getModifier();
      int expectedModifier = modifier*1;
      int resultModifier = ChoiceHandler.applyDifficultyToModifier(modifier,Difficulty.EASY, EffectType.MALUS);

      assertEquals(expectedModifier,resultModifier);
    }
    @Test
    public void should_apply_difficulty_to_modifier_normal_malus() {
        int modifier = eventChoiceMalus.getEffects().get(0).getModifier();
        int expectedModifier = (int) (modifier*(1.2));
        int resultModifier = ChoiceHandler.applyDifficultyToModifier(modifier,Difficulty.NORMAL, EffectType.MALUS);

        assertEquals(expectedModifier,resultModifier);
    }
    @Test
    public void should_apply_difficulty_to_modifier_hard_malus() {
        int modifier = eventChoiceMalus.getEffects().get(0).getModifier();
        int expectedModifier = (int) (modifier*(1.5));
        int resultModifier = ChoiceHandler.applyDifficultyToModifier(modifier,Difficulty.HARD, EffectType.MALUS);

        assertEquals(expectedModifier,resultModifier);
    }

    @Test
    public void should_apply_difficulty_to_modifier_easy_bonus() {
        int modifier = eventChoiceBonus.getEffects().get(0).getModifier();
        int expectedModifier = modifier*1;
        int resultModifier = ChoiceHandler.applyDifficultyToModifier(modifier,Difficulty.EASY, EffectType.BONUS);

        assertEquals(expectedModifier,resultModifier);
    }
    @Test
    public void should_apply_difficulty_to_modifier_normal_bonus() {
        int modifier = eventChoiceBonus.getEffects().get(0).getModifier();
        int expectedModifier = (int) (modifier*(0.8));
        int resultModifier = ChoiceHandler.applyDifficultyToModifier(modifier,Difficulty.NORMAL, EffectType.BONUS);

        assertEquals(expectedModifier,resultModifier);
    }
    @Test
    public void should_apply_difficulty_to_modifier_hard_bonus() {
        int modifier = eventChoiceBonus.getEffects().get(0).getModifier();
        int expectedModifier = (int) (modifier*(0.7));
        int resultModifier = ChoiceHandler.applyDifficultyToModifier(modifier,Difficulty.HARD, EffectType.BONUS);

        assertEquals(expectedModifier,resultModifier);
    }
}