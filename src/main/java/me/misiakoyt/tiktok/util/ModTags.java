package me.misiakoyt.tiktok.util;

import me.misiakoyt.tiktok.TikTok;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> ASBESTOS =
                createTag("asbestos");
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(TikTok.MOD_ID, name));
        }
    }

}
