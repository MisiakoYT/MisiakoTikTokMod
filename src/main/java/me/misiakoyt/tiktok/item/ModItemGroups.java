package me.misiakoyt.tiktok.item;

import me.misiakoyt.tiktok.TikTok;
import me.misiakoyt.tiktok.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup ASBETOS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TikTok.MOD_ID, "asbestos"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.asbestos"))
                    .icon(() -> new ItemStack(ModItems.ASBESTOS_POWDER)).entries((displayContext, entries) -> {
                        entries.add(ModItems.ASBESTOS_POWDER);
                        entries.add(ModItems.ETERNIT_POWDER);

                        entries.add(ModBlocks.ASBESTOS_BLOCK);
                        entries.add(ModBlocks.RAW_ASBESTOS_BLOCK);
                        entries.add(ModBlocks.ETERNIT_TILE);
                        entries.add(ModBlocks.ETERNIT_BLOCK);
                        entries.add(ModBlocks.CONCRETE_MIXER);

                        entries.add(ModItems.GLASS_HELMET);

                        entries.add(ModItems.LIGHTING);
                        entries.add(ModItems.LIGHTING_ARROW);


                    }).build());

    public static void registerItemGroups() {
        TikTok.LOGGER.info("Registering Item Groups for " + TikTok.MOD_ID);
    }

}
