package net.dernevs.ds.block.special;

import net.dernevs.ds.block.entity.DSBlockEntities;
import net.dernevs.ds.block.entity.NetherTableBE;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NetherTableBlock extends BlockWithEntity implements BlockEntityProvider {
    public NetherTableBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NetherTableBE(pos, state);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL; //makes block visible
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) { //drops items when BE broken
        if (state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof NetherTableBE){
                ItemScatterer.spawn(world,pos, (NetherTableBE)blockEntity);
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
            if (blockEntity instanceof NetherTableBE) {
                player.openHandledScreen((NetherTableBE)blockEntity); }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, DSBlockEntities.NETHER_TABLE_BLOCK_ENTITY ,(world1, pos, state1, blockEntity) -> blockEntity.tick(world1,pos,state1));
    }
}
