package net.dernevs.ds.screen;

import net.dernevs.ds.block.entity.NetherTableBE;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

public class NetherTableScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final NetherTableBE be;
    public NetherTableScreenHandler(int syncId, PlayerInventory inv, PacketByteBuf buf) {
        this(syncId, inv, inv.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(2));
    }

    public NetherTableScreenHandler(int syncId, PlayerInventory inv, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(DSScreenHandlers.NETHER_TABLE_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 10);
        this.inventory = (Inventory)blockEntity;
        this.propertyDelegate = propertyDelegate;
        this.be = ((NetherTableBE) blockEntity);

        this.addSlot(new Slot(inventory, 0,30,17));
        this.addSlot(new Slot(inventory, 1,48,17));
        this.addSlot(new Slot(inventory, 2,66,17));

        this.addSlot(new Slot(inventory, 3,30,35));
        this.addSlot(new Slot(inventory, 4,48,35));
        this.addSlot(new Slot(inventory, 5,66,35));

        this.addSlot(new Slot(inventory, 6,30,53));
        this.addSlot(new Slot(inventory, 7,48,53));
        this.addSlot(new Slot(inventory, 8,66,53));



        this.addSlot(new Slot(inventory, 9,124,35));


        addPlayerHotbar(inv);
        addPlayerInventory(inv);

        addProperties(propertyDelegate);
    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) { //the same every time aka just copy-paste this :P
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
    private void addPlayerInventory(PlayerInventory playerInventory) { //always the same for basic guis
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) { //read "addPlayerInventory Note"
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
