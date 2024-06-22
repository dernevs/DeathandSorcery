package net.dernevs.ds.enchantment;

import net.dernevs.ds.DeathAndSorcery;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DSEnchantments {
    public static final Enchantment IRON_LUNG_ENCHANTMENT = register("iron_lung_enchantment",
            new IronLungEnchantment(Enchantment.Rarity.VERY_RARE,
                    EnchantmentTarget.ARMOR_CHEST, EquipmentSlot.CHEST));
    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(DeathAndSorcery.MOD_ID, name), enchantment);
    }
    public static void registerModEnchanments() {
        DeathAndSorcery.LOGGER.info("Registering ModEnchantments for " + DeathAndSorcery.MOD_ID);
    }
}
