package me.misiakoyt.tiktok.block.entity;

import me.misiakoyt.tiktok.TikTok;
import me.misiakoyt.tiktok.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {

    public static BlockEntityType<ConcreteMixerBlockEntity> CONCRETE_MIXER_ENTITY;

    public static void registerBlockEntities() {
        CONCRETE_MIXER_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(TikTok.MOD_ID, "concrete_mixer_be"),
                BlockEntityType.Builder.create(ConcreteMixerBlockEntity::new, ModBlocks.CONCRETE_MIXER).build(null)
        );
    }
}
