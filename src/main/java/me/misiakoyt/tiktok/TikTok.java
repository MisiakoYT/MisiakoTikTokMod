package me.misiakoyt.tiktok;

import me.misiakoyt.tiktok.block.ModBlocks;
import me.misiakoyt.tiktok.block.entity.ModBlockEntities;
import me.misiakoyt.tiktok.datagen.ModWorldGenerator;
import me.misiakoyt.tiktok.item.ModItemGroups;
import me.misiakoyt.tiktok.item.ModItems;
import me.misiakoyt.tiktok.screen.ModScreenHandlers;
import me.misiakoyt.tiktok.util.ModCustomTrades;
import me.misiakoyt.tiktok.util.ModTags;
import me.misiakoyt.tiktok.villager.ModVillagers;
import me.misiakoyt.tiktok.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
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
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();

		ModVillagers.registerVillagers();
		ModCustomTrades.registerCustomTrades();

		ServerTickEvents.START_WORLD_TICK.register(world -> {
			for (PlayerEntity player : world.getPlayers()) {
				applyPoisonEffectIfNearBlock(player, world);
			}
		});

	}

	private void applyPoisonEffectIfNearBlock(PlayerEntity player, ServerWorld world) {

		BlockPos playerPos = player.getBlockPos();

		int radius = 5;
		int radiusY = 3;

		ItemStack helmet = player.getInventory().getArmorStack(3); // Slot 3 to slot na hełm
		if (helmet.getItem() == ModItems.GLASS_HELMET) {
			return; // Gracz nosi hełm, więc efekt trucizny nie zostanie dodany
		}


		BlockPos.stream(playerPos.add(-radius, -radiusY, -radius), playerPos.add(radius, radiusY, radius)).forEach(pos -> {
			BlockState state = world.getBlockState(pos);
			if (state.isIn(ModTags.Blocks.ASBESTOS)) {
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 50, 1));
			}
		});
	}


}