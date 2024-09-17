package me.misiakoyt.tiktok;

import me.misiakoyt.tiktok.block.ModBlocks;
import me.misiakoyt.tiktok.datagen.ModWorldGenerator;
import me.misiakoyt.tiktok.item.ModItemGroups;
import me.misiakoyt.tiktok.item.ModItems;
import me.misiakoyt.tiktok.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
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

		ModWorldGeneration.generateModWorldGen();

		ServerTickEvents.START_WORLD_TICK.register(world -> {
			for (PlayerEntity player : world.getPlayers()) {
				applyPoisonEffectIfNearBlock(player, world);
			}
		});

	}

	private void applyPoisonEffectIfNearBlock(PlayerEntity player, ServerWorld world) {
		// Pobierz pozycję gracza
		BlockPos playerPos = player.getBlockPos();

		// Zasięg sprawdzania odległości (np. 5 bloków)
		int radius = 5;

		ItemStack helmet = player.getInventory().getArmorStack(3); // Slot 3 to slot na hełm
		if (helmet.getItem() == ModItems.GLASS_HELMET) {
			return; // Gracz nosi hełm, więc efekt trucizny nie zostanie dodany
		}

		// Sprawdzanie wszystkich bloków w zasięgu
		BlockPos.stream(playerPos.add(-radius, -radius, -radius), playerPos.add(radius, radius, radius)).forEach(pos -> {
			Block block = world.getBlockState(pos).getBlock();
			if (block == ModBlocks.ASBESTOS_BLOCK) {
				// Dodaj efekt trucizny, jeśli gracz jest blisko bloku
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 1));
			}
		});
	}

}