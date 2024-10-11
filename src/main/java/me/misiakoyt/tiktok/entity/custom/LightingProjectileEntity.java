package me.misiakoyt.tiktok.entity.custom;

import me.misiakoyt.tiktok.entity.ModEntities;
import me.misiakoyt.tiktok.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class LightingProjectileEntity extends ThrownItemEntity {
    public LightingProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public LightingProjectileEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.LIGHTING_PROJECTILE,livingEntity, world);
    }


    @Override
    protected Item getDefaultItem() {
        return ModItems.LIGHTING;
    }

    @Override
    public EntitySpawnS2CPacket createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient()) {
            // Tworzymy nową błyskawicę
            LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(this.getWorld());
            if (lightning != null) {
                // Ustawiamy pozycję błyskawicy na miejsce uderzenia w blok
                lightning.refreshPositionAfterTeleport(blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ());

                // Dodajemy błyskawicę do świata
                this.getWorld().spawnEntity(lightning);
            }
        }

        // Usuwamy ten obiekt po uderzeniu (możesz pominąć, jeśli chcesz, by pozostał w grze)
        this.discard();

        // Wywołujemy podstawową funkcjonalność tej metody
        super.onBlockHit(blockHitResult);
    }

}
