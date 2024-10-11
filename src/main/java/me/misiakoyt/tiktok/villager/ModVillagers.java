package me.misiakoyt.tiktok.villager;

import com.google.common.collect.ImmutableSet;
import me.misiakoyt.tiktok.TikTok;
import me.misiakoyt.tiktok.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {

    public static final RegistryKey<PointOfInterestType> DEVELOPER_POI_KEY = poiKey("developerpoi");
    public static final PointOfInterestType SOUND_POI = registerPoi("developerpoi", ModBlocks.CONCRETE_MIXER);

    public static final VillagerProfession DEVELOPER_MASTER = registerProfession("developer_master", DEVELOPER_POI_KEY);

    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(TikTok.MOD_ID, name),
                new VillagerProfession(name, entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_SHEPHERD));
    }

    private static PointOfInterestType registerPoi(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(TikTok.MOD_ID, name), 1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> poiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier(TikTok.MOD_ID, name));
    }

    public static void registerVillagers() {
        TikTok.LOGGER.info("Registering villagers " + TikTok.MOD_ID);
    }

}
