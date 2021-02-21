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


        Long manualModifier = Long.valueOf(20);
        Effect manualEffect = new Effect("FACTION","religious","satisfaction",manualModifier,
                "FLAT","BONUS");

        Effect fetch_effect = new Effect(target.toString(),factionName,attribute,modifierLong,
                modifierType.toString(),effectType.toString());

        assertEquals(manualEffect.getTarget(),fetch_effect.getTarget());
        assertEquals(manualEffect.getEffectType(),fetch_effect.getEffectType());
        assertEquals(manualEffect.getAttribute(),fetch_effect.getAttribute());
        assertEquals(manualEffect.getModifier(),fetch_effect.getModifier());
        assertEquals(manualEffect.getFactionName(),fetch_effect.getFactionName());
        assertEquals(manualEffect.getModifierType(),fetch_effect.getModifierType());

    }


}