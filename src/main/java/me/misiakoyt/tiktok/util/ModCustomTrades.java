package me.misiakoyt.tiktok.util;

import me.misiakoyt.tiktok.block.ModBlocks;
import me.misiakoyt.tiktok.item.ModItems;
import me.misiakoyt.tiktok.villager.ModVillagers;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;

public class ModCustomTrades {

    public static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEVELOPER_MASTER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModBlocks.ASBESTOS_BLOCK, 32),
                            new ItemStack(Items.EMERALD, 1),
                            6, 5, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Blocks.SAND, 38),
                            new ItemStack(Items.EMERALD, 1),
                            6, 5, 0.05f));

                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEVELOPER_MASTER, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6),
                            new ItemStack(Blocks.SAND, 32),
                            6, 5, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 9),
                            new ItemStack(Blocks.GRAVEL, 34),
                            6, 5, 0.05f));

                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEVELOPER_MASTER, 3,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(Items.WATER_BUCKET, 1),
                            6, 5, 0.05f));

                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEVELOPER_MASTER, 4,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 8),
                            new ItemStack(Blocks.BLACK_CONCRETE, 16),
                            6, 5, 0.05f));

                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEVELOPER_MASTER, 5,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 5),
                            new ItemStack(Blocks.BRICKS, 16),
                            6, 5, 0.05f));

                });


    }

}
