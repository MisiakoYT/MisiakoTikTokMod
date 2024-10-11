package me.misiakoyt.tiktok.screen;

import me.misiakoyt.tiktok.TikTok;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static final ScreenHandlerType<ConcreteMixerScreenHandler> CONCRETE_MIXER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(TikTok.MOD_ID, "concrete_mixing"),
                    new ExtendedScreenHandlerType<>(ConcreteMixerScreenHandler::new));

    public static void registerScreenHandlers() {
        TikTok.LOGGER.info("Registering Screen Handlers for " + TikTok.MOD_ID);
    }
}
