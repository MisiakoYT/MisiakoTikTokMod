package me.misiakoyt.tiktok;

import me.misiakoyt.tiktok.entity.ModEntities;
import me.misiakoyt.tiktok.screen.ConcreteMixerScreen;
import me.misiakoyt.tiktok.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class TikTokClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.CONCRETE_MIXER_SCREEN_HANDLER, ConcreteMixerScreen::new);

        EntityRendererRegistry.register(ModEntities.LIGHTING_PROJECTILE, FlyingItemEntityRenderer::new);

    }
}
