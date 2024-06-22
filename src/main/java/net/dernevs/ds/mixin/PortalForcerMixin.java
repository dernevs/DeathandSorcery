package net.dernevs.ds.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.PortalForcer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PortalForcer.class)
public class PortalForcerMixin {
    @Inject(method = "createPortal", at = @At("HEAD"))
    public void anything(BlockPos pos, Direction.Axis axis, CallbackInfoReturnable<Optional<BlockLocating.Rectangle>> cir){

    }
}
