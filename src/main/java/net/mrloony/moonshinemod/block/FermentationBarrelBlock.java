package net.mrloony.moonshinemod.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.mrloony.moonshinemod.block.ModBlockEntities;
import net.mrloony.moonshinemod.block.entity.FermentationBarrelBlockEntity;

public class FermentationBarrelBlock extends BlockWithEntity {

    public FermentationBarrelBlock(FabricBlockSettings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FermentationBarrelBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.FERMENTATION_BARREL_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1, blockEntity));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof FermentationBarrelBlockEntity barrel) {
                ItemStack stack = player.getStackInHand(hand);

                if (stack.isOf(Items.WATER_BUCKET)) {
                    barrel.setWaterLevel(3);
                    if (!player.getAbilities().creativeMode) {
                        player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                    }
                } else if (stack.isOf(Items.BUCKET) && barrel.getWaterLevel() > 0) {
                    barrel.setWaterLevel(barrel.getWaterLevel() - 1);
                    if (!player.getAbilities().creativeMode) {
                        ItemStack waterBucket = new ItemStack(Items.WATER_BUCKET);
                        stack.decrement(1);
                        if (!player.getInventory().insertStack(waterBucket)) {
                            player.dropItem(waterBucket, false);
                        }
                    }
                } else if (stack.isOf(Items.SUGAR)) {
                    barrel.setSugar(barrel.getSugar() + 1);
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                } else if (stack.getItem().getTranslationKey().contains("yeast")) {
                    barrel.setHasYeast(true);
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                }

                barrel.markDirty();
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
