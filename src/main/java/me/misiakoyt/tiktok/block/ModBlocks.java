package me.misiakoyt.tiktok.block;

import me.misiakoyt.tiktok.TikTok;
import me.misiakoyt.tiktok.block.custom.ConcreteMixerBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.CobwebBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {


    public static final Block ASBESTOS_BLOCK = registerBlock("asbestos_block",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));

    public static final Block RAW_ASBESTOS_BLOCK = registerBlock("raw_asbestos_block",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));

    public static final Block ETERNIT_TILE = registerBlock("eternit_tile", new EternitTileBlock(FabricBlockSettings.copyOf(Blocks.STONE_SLAB)));

    public static final Block ETERNIT_BLOCK = registerBlock("eternit_block",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));

    public static final Block CONCRETE_MIXER = registerBlock("concrete_mixer",
            new ConcreteMixerBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TikTok.MOD_ID, name), block);
    }



    private static Item registerBlockItem(String name, Block block) {

        return Registry.register(Registries.ITEM, new Identifier(TikTok.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));

    }

    public static void registerModBlocks() {
        TikTok.LOGGER.info("Registering ModBlocks for " + TikTok.MOD_ID);
    }

}
