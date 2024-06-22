package net.dernevs.ds.effect;

import net.dernevs.ds.util.DamageTypeRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.text.Text;
import net.minecraft.world.event.Vibrations;

public class DustLungEffect extends StatusEffect {
    protected DustLungEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getWorld().getTimeOfDay() % 60 == 0) {
            entity.damage(entity.getDamageSources().create(DamageTypeRegistry.DUST_LUNG_DAMAGE), 1f);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) { //calls "applyupdateEffect" as long as it's true
        return true;
    }
}
