package net.dernevs.ds.block.entity;

import net.dernevs.ds.DeathAndSorcery;
import net.dernevs.ds.block.DSBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DSBlockEntities {

    public static final BlockEntityType<TradingBlockBE> TRADING_BLOCK_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(DeathAndSorcery.MOD_ID, "trading_block_block_entity"),
                    FabricBlockEntityTypeBuilder.create(TradingBlockBE::new,
                            DSBlocks.TRADING_BLOCK).build(null));
    public static final BlockEntityType<NetherTableBE> NETHER_TABLE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(DeathAndSorcery.MOD_ID, "nether_table_block_entity"),
                    FabricBlockEntityTypeBuilder.create(NetherTableBE::new,
                            DSBlocks.NETHER_TABLE).build(null));
    public static final BlockEntityType<NetherForgeBE> NETHER_FORGE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(DeathAndSorcery.MOD_ID, "nether_forge_block_entity"),
                    FabricBlockEntityTypeBuilder.create(NetherForgeBE::new,
                            DSBlocks.NETHER_FORGE).build(null));

    public static void registerBlockEntities() {
        DeathAndSorcery.LOGGER.info("Registering Block Entities for " + DeathAndSorcery.MOD_ID);
    }
}
