package net.dernevs.ds;

import net.dernevs.ds.block.DSBlocks;
import net.dernevs.ds.block.entity.DSBlockEntities;
import net.dernevs.ds.effect.DSEffects;
import net.dernevs.ds.enchantment.DSEnchantments;
import net.dernevs.ds.item.DSItemgroup;
import net.dernevs.ds.item.DSItems;
import net.dernevs.ds.recipe.DSRecipes;
import net.dernevs.ds.screen.DSScreenHandlers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeathAndSorcery implements ModInitializer {
	public static final String MOD_ID = "ds";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		DSItemgroup.registerItemGroups();

		DSItems.registerDSItems();
		DSBlocks.registerModBlocks();
		//DSRegistries.registerModThingies();
		DSScreenHandlers.registerScreenHandlers();
        DSBlockEntities.registerBlockEntities();
		DSRecipes.registerRecipes();
		DSEnchantments.registerModEnchanments();
		DSEffects.registerStatusEffect();
	}
}