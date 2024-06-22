package net.dernevs.ds.mixin;

import net.dernevs.ds.effect.DSEffects;
import net.dernevs.ds.enchantment.DSEnchantments;
import net.dernevs.ds.item.DSItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void modTickAdditions(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack a = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack b = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack c = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack d = player.getEquippedStack(EquipmentSlot.FEET);
        if (player.getWorld().getRegistryKey() == World.END && player.getY() <= 0 &&
                a.isOf(DSItems.SHULKERITE_HELMET) && b.isOf(DSItems.SHULKERITE_CHESTPLATE) &&
                c.isOf(DSItems.SHULKERITE_LEGGINGS) && d.isOf(DSItems.SHULKERITE_BOOTS)) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 300, 4, false, false, true));
                a.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
                b.damage(2, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
                c.damage(2, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
                d.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
        }
        if (player.getWorld().getRegistryKey() == World.OVERWORLD && player.getY() <= 0 &&
                EnchantmentHelper.getLevel(DSEnchantments.IRON_LUNG_ENCHANTMENT, b) < 1) {
            player.addStatusEffect(new StatusEffectInstance(DSEffects.DUST_LUNG, 200,0,false, true, true));
        }
    }
}
