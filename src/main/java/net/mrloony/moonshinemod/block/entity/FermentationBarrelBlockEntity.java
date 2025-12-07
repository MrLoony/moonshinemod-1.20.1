package net.mrloony.moonshinemod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrloony.moonshinemod.block.ModBlockEntities;

public class FermentationBarrelBlockEntity extends BlockEntity implements BlockEntityTicker<FermentationBarrelBlockEntity> {
    private static final int FERMENTATION_TIME = 24000; // 20 minutes

    private int waterLevel;
    private int sugar;
    private boolean hasYeast;
    private boolean hasLid;
    private int fermentationTicks;
    private boolean isFermenting;
    private boolean isDone;

    public FermentationBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FERMENTATION_BARREL_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, FermentationBarrelBlockEntity blockEntity) {
        if (world == null || blockEntity == null || world.isClient) {
            return;
        }

        if (blockEntity.isDone) {
            blockEntity.isFermenting = false;
            return;
        }

        if (blockEntity.hasLid && blockEntity.waterLevel > 0 && blockEntity.sugar > 0 && blockEntity.hasYeast) {
            blockEntity.isFermenting = true;
            blockEntity.fermentationTicks++;

            if (blockEntity.fermentationTicks >= FERMENTATION_TIME) {
                blockEntity.isFermenting = false;
                blockEntity.isDone = true;
                return;
            }

            if (blockEntity.fermentationTicks % 40 == 0) {
                world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1f, 1f);
            }
        } else {
            blockEntity.isFermenting = false;
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("WaterLevel", waterLevel);
        nbt.putInt("Sugar", sugar);
        nbt.putBoolean("HasYeast", hasYeast);
        nbt.putBoolean("HasLid", hasLid);
        nbt.putInt("FermentationTicks", fermentationTicks);
        nbt.putBoolean("IsFermenting", isFermenting);
        nbt.putBoolean("IsDone", isDone);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        waterLevel = nbt.getInt("WaterLevel");
        sugar = nbt.getInt("Sugar");
        hasYeast = nbt.getBoolean("HasYeast");
        hasLid = nbt.getBoolean("HasLid");
        fermentationTicks = nbt.getInt("FermentationTicks");
        isFermenting = nbt.getBoolean("IsFermenting");
        isDone = nbt.getBoolean("IsDone");
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public boolean hasYeast() {
        return hasYeast;
    }

    public void setHasYeast(boolean hasYeast) {
        this.hasYeast = hasYeast;
    }

    public boolean hasLid() {
        return hasLid;
    }

    public void setHasLid(boolean hasLid) {
        this.hasLid = hasLid;
    }

    public int getFermentationTicks() {
        return fermentationTicks;
    }

    public void setFermentationTicks(int fermentationTicks) {
        this.fermentationTicks = fermentationTicks;
    }

    public boolean isFermenting() {
        return isFermenting;
    }

    public void setFermenting(boolean fermenting) {
        isFermenting = fermenting;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
