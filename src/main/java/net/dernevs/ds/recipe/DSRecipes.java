package net.dernevs.ds.recipe;

import com.google.gson.JsonObject;
import net.dernevs.ds.DeathAndSorcery;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DSRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(DeathAndSorcery.MOD_ID, NetherTableRecipe.Serializer.ID),
                NetherTableRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(DeathAndSorcery.MOD_ID, NetherTableRecipe.Type.ID),
                NetherTableRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(DeathAndSorcery.MOD_ID, NetherForgeRecipe.Serializer.ID),
                NetherForgeRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(DeathAndSorcery.MOD_ID, NetherForgeRecipe.Type.ID),
                NetherForgeRecipe.Type.INSTANCE);
    }
    public static final RecipeType<NetherForgeRecipe> NETHER_FORGING = RecipeType.register("nether_forging");

    public static <T extends Recipe<?>> RecipeType<T> register(final String id) {
        return Registry.register(Registries.RECIPE_TYPE, new Identifier(DeathAndSorcery.MOD_ID, id), new RecipeType<T>(){

            public String toString() {
                return id;
            }
        });
    }
}
