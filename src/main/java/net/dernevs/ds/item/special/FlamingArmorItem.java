package net.dernevs.ds.item.special;

import net.dernevs.ds.item.DSItems;
import net.dernevs.ds.item.materials.DSArmorMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class FlamingArmorItem extends ArmorItem {
    public FlamingArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        PlayerEntity player = ((PlayerEntity) entity);
        ItemStack a = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack b = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack c = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack d = player.getEquippedStack(EquipmentSlot.FEET);
            if (a.isOf(DSItems.FLAMING_HELMET) || b.isOf(DSItems.FLAMING_CHESTPLATE) ||
                c.isOf(DSItems.FLAMING_LEGGINGS) || d.isOf(DSItems.FLAMING_BOOTS)) {
                if (!entity.isInLava()) {
                    ((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 300, 0));
                }
            }
        }
    }
