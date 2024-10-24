package me.misiakoyt.tiktok.mixin;

import me.misiakoyt.tiktok.TikTok;
import me.misiakoyt.tiktok.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningEntity.class)
public class LightningEntityMixin {

	// Nasłuchujemy na metodę tick, która jest wywoływana, gdy piorun trafia w świat
	@Inject(method = "tick", at = @At("HEAD"))
	private void onLightningStrike(CallbackInfo ci) {
		LightningEntity entity = (LightningEntity) (Object) this;
		World world = entity.getWorld();
		TikTok.LOGGER.info("blyskawica");

		// Sprawdzamy, czy świat to serwerowy świat
		if (!world.isClient()) {
			BlockPos pos = entity.getBlockPos(); // Pozycja, gdzie uderza piorun
			BlockPos belowPos = pos.down(); // Pozycja bloku pod piorunem

			// Sprawdzamy, czy blok uderzenia to piorunochron
			if (world.getBlockState(pos).isOf(Blocks.LIGHTNING_ROD)) {
				TikTok.LOGGER.info("blyskawica w piorunochornj");
				// Sprawdzamy, czy pod piorunochronem jest blok złota
				if (world.getBlockState(belowPos).isOf(Blocks.GOLD_BLOCK)) {
					TikTok.LOGGER.info("zloto");
					// Tworzymy przedmiot do dropu (np. złoty samorodek)
					ItemStack itemStack = new ItemStack(ModItems.LIGHTING, 1);

					// Drop przedmiotu na świecie
					ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
					world.spawnEntity(itemEntity);
				}
			}
		}
	}
}