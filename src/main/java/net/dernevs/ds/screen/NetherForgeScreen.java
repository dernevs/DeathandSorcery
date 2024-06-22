package net.dernevs.ds.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dernevs.ds.DeathAndSorcery;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class NetherForgeScreen extends HandledScreen<NetherForgeScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(DeathAndSorcery.MOD_ID, "textures/gui/nether_forge_gui.png");
    public NetherForgeScreen(NetherForgeScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) { //I'm just praying this works
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F); //normal color
        RenderSystem.setShaderTexture(0, TEXTURE); //gets the texture to draw
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth , backgroundHeight);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 89, y + 34, 176, 0, handler.getScaledProgress(), 16);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
