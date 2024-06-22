package net.dernevs.ds.effect;

import net.dernevs.ds.DeathAndSorcery;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DSEffects {

    public static final StatusEffect DUST_LUNG = registerStatusEffect("dust_lung",
            new DustLungEffect(StatusEffectCategory.HARMFUL, 2039583));

    private static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(DeathAndSorcery.MOD_ID, name), statusEffect);
    }
    public static void registerStatusEffect() {
        DeathAndSorcery.LOGGER.info("Registering Effects for " + DeathAndSorcery.MOD_ID);
    }
}
