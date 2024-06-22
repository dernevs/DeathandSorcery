package net.dernevs.ds.block.entity;

import net.dernevs.ds.recipe.NetherTableRecipe;
import net.dernevs.ds.screen.NetherTableScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class NetherTableBE extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY); //amount of slots
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int INPUT_SLOT_4 = 3;
    private static final int INPUT_SLOT_5 = 4;
    private static final int INPUT_SLOT_6 = 5;
    private static final int INPUT_SLOT_7 = 6;
    private static final int INPUT_SLOT_8 = 7;
    private static final int INPUT_SLOT_9 = 8;
    private static final int OUTPUT_SLOT = 9;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxprogress = 720;


    public NetherTableBE(BlockPos pos, BlockState state) {
        super(DSBlockEntities.NETHER_TABLE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() { //syncs the variables
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> NetherTableBE.this.progress;
                    case 1 -> NetherTableBE.this.maxprogress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: NetherTableBE.this.progress = value;
                    case 1: NetherTableBE.this.maxprogress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.ds.nether_table");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new NetherTableScreenHandler(syncId,playerInventory,this, propertyDelegate);
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,inventory);
        nbt.putInt("nether_table.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("nether_table.progress");
        super.readNbt(nbt);
    }

    public void tick(World world1, BlockPos pos, BlockState state1) {
        if (canInsertIntoOutputSlot() && hasRecipe()) {
            increaseCraftingProgress();
            markDirty(world1, pos, state1);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void craftItem() {
        Optional<NetherTableRecipe> recipe = getCurrentRecipe();

        this.removeStack(INPUT_SLOT_1, 1);
        this.removeStack(INPUT_SLOT_2, 1);
        this.removeStack(INPUT_SLOT_3, 1);
        this.removeStack(INPUT_SLOT_4, 1);
        this.removeStack(INPUT_SLOT_5, 1);
        this.removeStack(INPUT_SLOT_6, 1);
        this.removeStack(INPUT_SLOT_7, 1);
        this.removeStack(INPUT_SLOT_8, 1);
        this.removeStack(INPUT_SLOT_9, 1);

        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().getOutput(null).getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + recipe.get().getOutput(null).getCount()));
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxprogress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<NetherTableRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;
        ItemStack output = recipe.get().getOutput(null);
        return canInsertAmountIntoOutputSlot(output.getCount()) &&
                canInsertItemIntoOutputSlot(output);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.getStack(OUTPUT_SLOT).getMaxCount() >= this.getStack(OUTPUT_SLOT).getCount() + count;
    }

    private Optional<NetherTableRecipe> getCurrentRecipe() {
        SimpleInventory inventory = new SimpleInventory((this.size()));
        for (int i = 0; i < this.size(); i++) {
            inventory.setStack(i, this.getStack(i));
        }

        return this.getWorld().getRecipeManager().getFirstMatch(NetherTableRecipe.Type.INSTANCE, inventory, this.getWorld());
    }

    private boolean canInsertIntoOutputSlot() {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }
}
