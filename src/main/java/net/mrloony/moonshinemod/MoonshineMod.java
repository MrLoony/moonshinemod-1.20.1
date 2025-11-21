package net.mrloony.moonshinemod;

import net.fabricmc.api.ModInitializer;

import net.mrloony.moonshinemod.block.ModBlocks;
import net.mrloony.moonshinemod.item.ModItemGroup;
import net.mrloony.moonshinemod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoonshineMod implements ModInitializer {
	public static final String MOD_ID = "moonshinemod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItemGroup.registerItemsGroups();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
	}
}