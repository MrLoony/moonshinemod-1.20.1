package net.mrloony.moonshinemod.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrloony.moonshinemod.MoonshineMod;
import net.mrloony.moonshinemod.block.entity.FermentationBarrelBlockEntity;

public class ModBlockEntities {
    public static BlockEntityType<FermentationBarrelBlockEntity> FERMENTATION_BARREL_BLOCK_ENTITY;

    public static void registerBlockEntities() {
        FERMENTATION_BARREL_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(MoonshineMod.MOD_ID, "fermentation_barrel_block_entity"),
            FabricBlockEntityTypeBuilder.create(
                FermentationBarrelBlockEntity::new,
                ModBlocks.FERMENTATION_BARREL_BLOCK)
                .build(null)
        );
    }
}
