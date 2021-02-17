package com.esgi.data;

import com.esgi.data.enums.EffectType;
import com.esgi.data.enums.ModifierType;
import com.esgi.data.enums.Target;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EffectTest {

    @Test
    public void should_create_1_effect_from_tsar(){
        Map<Integer, Event> events = Loader.fetchEvents("tsar");
        Effect effect = events.get(1).getChoices().get(0).getEffects().get(0);
        Target target = effect.getTarget();
       EffectType effectType = effect.getEffectType();
       String factionName = effect.getFactionName();
        int modifier = effect.getModifier();
        Long modifierLong = Long.valueOf(modifier);
        ModifierType modifierType = effect.getModifierType();
        String attribute = effect.getAttribute();


        Long manual_modifier = Long.valueOf(30);
        Effect manual_effect = new Effect("FACTION","religious","satisfaction",manual_modifier,
                "FLAT","BONUS");

        Effect fetch_effect = new Effect(target.toString(),factionName,attribute,modifierLong,
                modifierType.toString(),effectType.toString());

        assertEquals(manual_effect.getTarget(),fetch_effect.getTarget());
        assertEquals(manual_effect.getEffectType(),fetch_effect.getEffectType());
        assertEquals(manual_effect.getAttribute(),fetch_effect.getAttribute());
        assertEquals(manual_effect.getModifier(),fetch_effect.getModifier());
        assertEquals(manual_effect.getFactionName(),fetch_effect.getFactionName());
        assertEquals(manual_effect.getModifierType(),fetch_effect.getModifierType());

    }


}