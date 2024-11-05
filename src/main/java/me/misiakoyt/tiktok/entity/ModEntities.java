package me.misiakoyt.tiktok.entity;

import me.misiakoyt.tiktok.TikTok;
import me.misiakoyt.tiktok.entity.custom.LightingProjectileEntity;
import me.misiakoyt.tiktok.entity.projectile.LightingArrowEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<LightingProjectileEntity> LIGHTING_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TikTok.MOD_ID, "lighting_projectiles"),
            FabricEntityTypeBuilder.<LightingProjectileEntity>create(SpawnGroup.MISC, LightingProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());


}
