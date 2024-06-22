package net.dernevs.ds.item;

import net.dernevs.ds.DeathAndSorcery;
import net.dernevs.ds.item.materials.DSArmorMaterial;
import net.dernevs.ds.item.special.FlamingArmorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DSItems {
    public static final Item PURE_EMERALD = registerItem("pure_emerald",
            new Item(new FabricItemSettings()));
    public static final Item TOTEM_FRAGMENT = registerItem("totem_fragment",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item GILDED_NETHERITE_INGOT = registerItem("gilded_netherite_ingot",
            new Item(new FabricItemSettings()));
    public static final Item FLAMING_NETHERITE_INGOT = registerItem("flaming_netherite_ingot",
            new Item(new FabricItemSettings()));
    public static final Item SHULKERITE_INGOT = registerItem("shulkerite_ingot",
            new Item(new FabricItemSettings()));
    public static final Item ENDER_INGOT = registerItem("ender_ingot",
            new Item(new FabricItemSettings()));
    public static final Item SHULKERITE_SWORD = registerItem("shulkerite_sword",
            new SwordItem(ToolMaterials.IRON,1,1,new FabricItemSettings()));
    public static final Item SHULKERITE_HELMET = registerItem("shulkerite_helmet",
            new ArmorItem(DSArmorMaterial.SHULKERITE, ArmorItem.Type.HELMET ,new FabricItemSettings()));
    public static final Item SHULKERITE_CHESTPLATE = registerItem("shulkerite_chestplate",
            new ArmorItem(DSArmorMaterial.SHULKERITE, ArmorItem.Type.CHESTPLATE ,new FabricItemSettings()));
    public static final Item SHULKERITE_LEGGINGS = registerItem("shulkerite_leggings",
            new ArmorItem(DSArmorMaterial.SHULKERITE, ArmorItem.Type.LEGGINGS ,new FabricItemSettings()));
    public static final Item SHULKERITE_BOOTS = registerItem("shulkerite_boots",
            new ArmorItem(DSArmorMaterial.SHULKERITE, ArmorItem.Type.BOOTS ,new FabricItemSettings()));
    public static final Item GILDED_HELMET = registerItem("gilded_netherite_helmet",
            new ArmorItem(DSArmorMaterial.GILDED, ArmorItem.Type.HELMET ,new FabricItemSettings()));
    public static final Item GILDED_CHESTPLATE = registerItem("gilded_netherite_chestplate",
            new ArmorItem(DSArmorMaterial.GILDED, ArmorItem.Type.CHESTPLATE ,new FabricItemSettings()));
    public static final Item GILDED_LEGGINGS = registerItem("gilded_netherite_leggings",
            new ArmorItem(DSArmorMaterial.GILDED, ArmorItem.Type.LEGGINGS ,new FabricItemSettings()));
    public static final Item GILDED_BOOTS = registerItem("gilded_netherite_boots",
            new ArmorItem(DSArmorMaterial.GILDED, ArmorItem.Type.BOOTS ,new FabricItemSettings()));
    public static final Item FLAMING_HELMET = registerItem("flaming_netherite_helmet",
            new FlamingArmorItem(DSArmorMaterial.FLAMING, ArmorItem.Type.HELMET ,new FabricItemSettings()));
    public static final Item FLAMING_CHESTPLATE = registerItem("flaming_netherite_chestplate",
            new FlamingArmorItem(DSArmorMaterial.FLAMING, ArmorItem.Type.CHESTPLATE ,new FabricItemSettings()));
    public static final Item FLAMING_LEGGINGS = registerItem("flaming_netherite_leggings",
            new FlamingArmorItem(DSArmorMaterial.FLAMING, ArmorItem.Type.LEGGINGS ,new FabricItemSettings()));
    public static final Item FLAMING_BOOTS = registerItem("flaming_netherite_boots",
            new FlamingArmorItem(DSArmorMaterial.FLAMING, ArmorItem.Type.BOOTS ,new FabricItemSettings()));
    public static final Item MINERS_DREAM = registerItem("miners_dream",
            new PickaxeItem(ToolMaterials.IRON, 1,1, new Item.Settings()));
    public static final Item SCULK_CORE = registerItem("sculk_core",
            new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(DeathAndSorcery.MOD_ID, name), item);
    }

    public static void registerDSItems() {
        DeathAndSorcery.LOGGER.info("Register mod items for " + DeathAndSorcery.MOD_ID);
    }
}
