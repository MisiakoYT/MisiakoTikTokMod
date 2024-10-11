package me.misiakoyt.tiktok.item;

import me.misiakoyt.tiktok.TikTok;
import me.misiakoyt.tiktok.item.custom.LightingItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item ASBESTOS_POWDER = registerItem("asbestos_powder", new Item(new FabricItemSettings()));
    public static final Item ETERNIT_POWDER = registerItem("eternit_powder", new Item(new FabricItemSettings()));

    private static void addItemsToIngredItemGroup(FabricItemGroupEntries entries) {
        entries.add(ASBESTOS_POWDER);
    }
    public static final Item GLASS_HELMET = registerItem("glass_helmet",
            new ArmorItem(ModArmorMaterial.GLASS,ArmorItem.Type.HELMET, new FabricItemSettings().maxCount(1)));

    public static final Item LIGHTING = registerItem("lighting", new LightingItem(new FabricItemSettings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TikTok.MOD_ID, name), item);
    }
    public static void registerModItems() {
        TikTok.LOGGER.info("Registering Mod Items for " + TikTok.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredItemGroup);
    }

}
