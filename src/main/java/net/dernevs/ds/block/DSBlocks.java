package net.dernevs.ds.block;

import net.dernevs.ds.DeathAndSorcery;
import net.dernevs.ds.block.special.NetherForgeBlock;
import net.dernevs.ds.block.special.NetherTableBlock;
import net.dernevs.ds.block.special.TradingBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DSBlocks {
    public static final Block TRADING_BLOCK = registerBlock("trading_block",
            new TradingBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS).nonOpaque()), true);
    public static final Block NETHER_TABLE = registerBlock("nether_table",
            new NetherTableBlock(FabricBlockSettings.copyOf(Blocks.BLACKSTONE)), true);
    public static final Block NETHER_FORGE = registerBlock("nether_forge",
            new NetherForgeBlock(FabricBlockSettings.copyOf(Blocks.BLACKSTONE)), true);
    public static final Block GILDED_NETHERITE_BLOCK = registerBlock("gilded_netherite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK)), true);
    public static final Block FLAMING_NETHERITE_BLOCK = registerBlock("flaming_netherite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK)), true);
    public static final Block SHULKERITE_BLOCK = registerBlock("shulkerite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)), true);
    public static final Block ENDER_BLOCK = registerBlock("ender_block",
            new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)), true);



    private static Block registerBlock(String name, Block block, boolean item) {
        if(item){
            Registry.register((Registries.ITEM), new Identifier(DeathAndSorcery.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        }
        return Registry.register(Registries.BLOCK, new Identifier(DeathAndSorcery.MOD_ID, name), block);
    }

    public static void registerModBlocks(){
        DeathAndSorcery.LOGGER.info("Registering ModBlocks for " + DeathAndSorcery.MOD_ID);
    }

}
