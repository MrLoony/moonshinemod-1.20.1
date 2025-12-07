package net.mrloony.moonshinemod.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.mrloony.moonshinemod.block.entity.FermentationBarrelBlockEntity;

public class FermentationLidBlock extends Block {

    public FermentationLidBlock(FabricBlockSettings settings) {
        super(settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isOf(ModBlocks.FERMENTATION_BARREL_BLOCK);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
            var blockEntity = world.getBlockEntity(pos.down());
            if (blockEntity instanceof FermentationBarrelBlockEntity barrelBlockEntity) {
                barrelBlockEntity.setHasLid(true);
            }
        }
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        if (!world.isClient()) {
            var blockEntity = world.getBlockEntity(pos.down());
            if (blockEntity instanceof FermentationBarrelBlockEntity barrelBlockEntity) {
                barrelBlockEntity.setHasLid(false);
            }
        }
    }
}
