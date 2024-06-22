package net.dernevs.ds.item.special;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnderTransporterItem extends Item {
    public EnderTransporterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.isSneaking() && !world.isClient){
            double x = user.getX();
            double y = user.getY();
            double z = user.getZ();

            int damage =stack.getDamage();
            x = Math.round(x);
            y = Math.round(y);
            z = Math.round(z);

            NbtCompound nbtData = new NbtCompound();
            nbtData.putDouble("ds.saved.coordinate.x1", x);
            nbtData.putDouble("ds.saved.coordinate.y1", y);
            nbtData.putDouble("ds.saved.coordinate.z1", z);
            stack.setNbt(nbtData);
            stack.setDamage(damage);
        }

        if (!user.isSneaking()){
            double xpos = stack.getNbt().getDouble("ds.saved.coordinate.x1");
            double ypos = stack.getNbt().getDouble("ds.saved.coordinate.y1");
            double zpos = stack.getNbt().getDouble("ds.saved.coordinate.z1");
            user.teleport(xpos,ypos,zpos);
            stack.damage(1, user, p -> p.sendToolBreakStatus(hand));
        }

        return super.use(world, user, hand);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(stack.hasNbt()) {
            Double SavedCoordinateX1 = stack.getNbt().getDouble("ds.saved.coordinate.x1");
            Double SavedCoordinateY1 = stack.getNbt().getDouble("ds.saved.coordinate.y1");
            Double SavedCoordinateZ1 = stack.getNbt().getDouble("ds.saved.coordinate.z1");

            tooltip.add(Text.literal("Overworld X:" + String.valueOf(SavedCoordinateX1) + " Y:" + String.valueOf(SavedCoordinateY1) + " Z:" + String.valueOf(SavedCoordinateZ1)));
        }
    }
}