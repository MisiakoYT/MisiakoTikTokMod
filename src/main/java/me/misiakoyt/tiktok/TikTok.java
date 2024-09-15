package me.misiakoyt.tiktok;

import me.misiakoyt.tiktok.block.ModBlocks;
import me.misiakoyt.tiktok.item.ModItemGroups;
import me.misiakoyt.tiktok.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TikTok implements ModInitializer {
	public static final String MOD_ID = "tiktok";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

	}
}