package net.dernevs.ds;

import net.dernevs.ds.screen.DSScreenHandlers;
import net.dernevs.ds.screen.NetherForgeScreen;
import net.dernevs.ds.screen.NetherTableScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;


public class DeathAndSorceryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(DSScreenHandlers.NETHER_TABLE_SCREEN_HANDLER, NetherTableScreen::new);
        HandledScreens.register(DSScreenHandlers.NETHER_FORGE_SCREEN_HANDLER, NetherForgeScreen::new);
        //EntityRendererRegistry.register(DSEntities.DEATH_CARD_ITEM, FlyingItemEntityRenderer::new);
    }
}

