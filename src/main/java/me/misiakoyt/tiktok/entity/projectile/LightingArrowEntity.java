package me.misiakoyt.tiktok.entity.projectile;

import me.misiakoyt.tiktok.entity.ModEntities;
import me.misiakoyt.tiktok.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class LightingArrowEntity extends PersistentProjectileEntity {

    private static final ItemStack DEFAULT_STACK = new ItemStack(ModItems.LIGHTING_ARROW);
    public LightingArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world, DEFAULT_STACK);
    }

    public LightingArrowEntity(World world, double x, double y, double z, ItemStack stack) {
        super(EntityType.ARROW, x, y, z, world, stack);
    }

    public LightingArrowEntity(World world, LivingEntity owner, ItemStack stack) {
        super(EntityType.ARROW, owner, world, stack);
    }


    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        // Usuwanie strzały po uderzeniu w obiekt, blok lub istotę
        if (hitResult.getType() == HitResult.Type.BLOCK || hitResult.getType() == HitResult.Type.ENTITY) {
            // Usuwanie strzały tylko po uderzeniu w blok lub istotę
            double x = hitResult.getPos().x;
            double y = hitResult.getPos().y;
            double z = hitResult.getPos().z;

            getWorld().createExplosion(this, x, y, z, 2.0F, true, World.ExplosionSourceType.BLOCK);



            for(int i=0; i < 3; i++) {

                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(this.getWorld());

                if(lightning != null) {
                    x = x + (Math.random() * 10) - 5;
                    z = z + (Math.random() * 10) - 5;

                    lightning.refreshPositionAfterTeleport(x, y, z);

                    this.getWorld().spawnEntity(lightning);
                }

            }

            this.discard();
        }
    }


}