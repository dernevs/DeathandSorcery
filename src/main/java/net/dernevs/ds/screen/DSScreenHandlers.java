package net.dernevs.ds.screen;

import net.dernevs.ds.DeathAndSorcery;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class DSScreenHandlers {
    public static final ScreenHandlerType<NetherTableScreenHandler> NETHER_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(DeathAndSorcery.MOD_ID, "nether_table_screen_handler"),
                    new ExtendedScreenHandlerType<>(NetherTableScreenHandler::new));
    public static final ScreenHandlerType<NetherForgeScreenHandler> NETHER_FORGE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(DeathAndSorcery.MOD_ID, "nether_forge_screen_handler"),
                    new ExtendedScreenHandlerType<>(NetherForgeScreenHandler::new));

    public static void registerScreenHandlers() {
        DeathAndSorcery.LOGGER.info("Registering Screen Handlers for " + DeathAndSorcery.MOD_ID);
    }
}
