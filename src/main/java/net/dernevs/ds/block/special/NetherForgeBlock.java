package net.dernevs.ds.block.special;

import net.dernevs.ds.block.entity.DSBlockEntities;
import net.dernevs.ds.block.entity.NetherForgeBE;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NetherForgeBlock extends BlockWithEntity implements BlockEntityProvider {
    public NetherForgeBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NetherForgeBE(pos, state);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL; //makes block visible
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) { //drops items when BE broken
        if (state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof NetherForgeBE){
                ItemScatterer.spawn(world,pos, (NetherForgeBE)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) { //opens BE
        if (!world.isClient) {
            /*
            NamedScreenHandlerFactory screenHandlerFactory = ((NetherTableBE) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
             */
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof NetherForgeBE) {
                player.openHandledScreen((NetherForgeBE)blockEntity); }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, DSBlockEntities.NETHER_FORGE_BLOCK_ENTITY ,(world1, pos, state1, blockEntity) -> NetherForgeBE.tick(world1,pos,state1,blockEntity));
    }
}
