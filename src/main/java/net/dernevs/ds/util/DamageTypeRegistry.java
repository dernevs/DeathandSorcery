package net.dernevs.ds.util;

import net.dernevs.ds.DeathAndSorcery;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class DamageTypeRegistry {
    public static final RegistryKey<DamageType> DEATH_CARD_DAMAGE = registerDamageType("death_kill");
    public static final RegistryKey<DamageType> DUST_LUNG_DAMAGE = registerDamageType("dust_lung_damage");

    //public static final RegistryKey<DamageType> DAMAGE_NAME = registerDamageType("damage_for_js");

    private static RegistryKey<DamageType> registerDamageType(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(DeathAndSorcery.MOD_ID, name));
    }
}
