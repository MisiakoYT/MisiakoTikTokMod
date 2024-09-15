package me.misiakoyt.tiktok.item;

import me.misiakoyt.tiktok.TikTok;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item ASBESTOS_POWDER = registerItem("asbestos_powder", new Item(new FabricItemSettings()));

    private static void addItemsToIngredItemGroup(FabricItemGroupEntries entries) {
        entries.add(ASBESTOS_POWDER);
    }


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TikTok.MOD_ID, name), item);
    }
    public static void registerModItems() {
        TikTok.LOGGER.info("Registering Mod Items for " + TikTok.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredItemGroup);
    }

}
