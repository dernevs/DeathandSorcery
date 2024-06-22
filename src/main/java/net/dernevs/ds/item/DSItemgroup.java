package net.dernevs.ds.item;

import net.dernevs.ds.DeathAndSorcery;
import net.dernevs.ds.block.DSBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DSItemgroup {
    public static final ItemGroup DS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(DeathAndSorcery.MOD_ID, "ds1"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ds1"))
                    .icon(() -> new ItemStack(DSBlocks.NETHER_TABLE)).entries((displayContext, entries) -> {

                        entries.add(DSBlocks.NETHER_TABLE);
                        //entries.add(DSBlocks.TRADING_BLOCK);
                        entries.add(DSItems.TOTEM_FRAGMENT);
                        entries.add(DSItems.PURE_EMERALD);
                        entries.add(DSItems.SHULKERITE_INGOT);
                        entries.add(DSBlocks.SHULKERITE_BLOCK);
                        entries.add(DSItems.SHULKERITE_HELMET);
                        entries.add(DSItems.SHULKERITE_CHESTPLATE);
                        entries.add(DSItems.SHULKERITE_LEGGINGS);
                        entries.add(DSItems.SHULKERITE_BOOTS);
                        entries.add(DSItems.SHULKERITE_SWORD);
                        entries.add(DSItems.FLAMING_NETHERITE_INGOT);
                        entries.add(DSBlocks.FLAMING_NETHERITE_BLOCK);
                        entries.add(DSItems.FLAMING_HELMET);
                        entries.add(DSItems.FLAMING_CHESTPLATE);
                        entries.add(DSItems.FLAMING_LEGGINGS);
                        entries.add(DSItems.FLAMING_BOOTS);
                        entries.add(DSItems.GILDED_NETHERITE_INGOT);
                        entries.add(DSBlocks.GILDED_NETHERITE_BLOCK);
                        entries.add(DSItems.GILDED_HELMET);
                        entries.add(DSItems.GILDED_CHESTPLATE);
                        entries.add(DSItems.GILDED_LEGGINGS);
                        entries.add(DSItems.GILDED_BOOTS);
                        entries.add(DSItems.ENDER_INGOT);
                        entries.add(DSBlocks.ENDER_BLOCK);
                        entries.add(DSItems.SCULK_CORE);
                        entries.add(DSItems.MINERS_DREAM);

                    }).build());
    public static final ItemGroup WIP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(DeathAndSorcery.MOD_ID, "wip"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.wip"))
                    .icon(() -> new ItemStack(Items.CLOCK)).entries((displayContext, entries) -> {
                        entries.add(DSBlocks.TRADING_BLOCK);
                    }).build());


                        public static void registerItemGroups () {
                            DeathAndSorcery.LOGGER.info("Register mod item groups for " + DeathAndSorcery.MOD_ID);
                        }
                    }
