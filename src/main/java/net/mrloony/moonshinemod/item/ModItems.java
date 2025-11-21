package net.mrloony.moonshinemod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrloony.moonshinemod.MoonshineMod;

public class ModItems {
    public static final Item MOONSHINE = registerItem("moonshine", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(MOONSHINE);
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MoonshineMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MoonshineMod.LOGGER.info("Registering Mod Items for" + MoonshineMod.MOD_ID);
    }
}
