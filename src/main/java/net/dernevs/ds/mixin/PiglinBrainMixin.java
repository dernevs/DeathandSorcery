package net.dernevs.ds.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.dernevs.ds.item.materials.DSArmorMaterial;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {
    @ModifyReturnValue(method = "wearsGoldArmor", at = @At("RETURN"))
    private static boolean addModArmorCheck(boolean original, LivingEntity entity) {
        Iterable<ItemStack> iterable = entity.getArmorItems();
        for (ItemStack itemStack : iterable) {
            Item item = itemStack.getItem();
            if (!(item instanceof ArmorItem) || ((ArmorItem)item).getMaterial() != ArmorMaterials.GOLD
                    && ((ArmorItem)item).getMaterial() != DSArmorMaterial.GILDED)
                continue;
            return true;
        }
        return false;
    }
}
