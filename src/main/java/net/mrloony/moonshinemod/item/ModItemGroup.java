package net.mrloony.moonshinemod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mrloony.moonshinemod.MoonshineMod;
import net.mrloony.moonshinemod.block.ModBlocks;

public class ModItemGroup {
    public static final ItemGroup MOONSHINE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(MoonshineMod.MOD_ID, "moonshine"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.moonshine"))
                    .icon(() -> new ItemStack(ModItems.MOONSHINE)).entries((displayContext, entries) -> {

                        entries.add(ModItems.MOONSHINE);

                        entries.add(ModBlocks.FERMENTATION_BARREL_BLOCK);

                    }).build());

    public static void registerItemsGroups() {
        MoonshineMod.LOGGER.info("Registering Item Group for " + MoonshineMod.MOD_ID);
    }
}
