package net.dernevs.ds.recipe;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class NetherForgeRecipe implements Recipe<Inventory> {
    protected final RecipeType<?> type;
    protected final Identifier id;
    private final CookingRecipeCategory category;
    protected final String group;
    protected final Ingredient input;
    protected final ItemStack output;
    protected final float experience;
    protected final int cookTime;

    public NetherForgeRecipe(RecipeType<?> type, Identifier id, String group, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime) {
        this.type = type;
        this.category = category;
        this.id = id;
        this.group = group;
        this.input = input;
        this.output = output;
        this.experience = experience;
        this.cookTime = cookTime;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return this.input.test(inventory.getStack(0));
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        return this.output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> defaultedList = DefaultedList.of();
        defaultedList.add(this.input);
        return defaultedList;
    }

    public float getExperience() {
        return this.experience;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return this.output;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return this.type;
    }
    public static class Type implements RecipeType<NetherForgeRecipe> {
        private Type() { }
        public static final NetherForgeRecipe.Type INSTANCE = new Type();
        public static final String ID = "nether_forging";
    }
    public static class Serializer implements RecipeSerializer<NetherForgeRecipe> {
        private final int cookingTime = 0;
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "nether_forging";
        // this is the name given in the json file

        @Override
        public NetherForgeRecipe read(Identifier id, JsonObject jsonObject) { //reads in the Json file
            String string = JsonHelper.getString(jsonObject, "group", "");
            CookingRecipeCategory cookingRecipeCategory = CookingRecipeCategory.CODEC.byId(JsonHelper.getString(jsonObject, "category", null), CookingRecipeCategory.MISC);
            JsonElement jsonElement = JsonHelper.hasArray(jsonObject, "ingredient") ? JsonHelper.getArray(jsonObject, "ingredient") : JsonHelper.getObject(jsonObject, "ingredient");
            Ingredient ingredient = Ingredient.fromJson(jsonElement, false);
            String string2 = JsonHelper.getString(jsonObject, "result");
            Identifier identifier2 = new Identifier(string2);
            ItemStack itemStack = new ItemStack((ItemConvertible) Registries.ITEM.getOrEmpty(identifier2).orElseThrow(() -> new IllegalStateException("Item: " + string2 + " does not exist")));
            float f = JsonHelper.getFloat(jsonObject, "experience", 0.0f);
            int i = JsonHelper.getInt(jsonObject, "cookingtime", this.cookingTime);
            return new NetherForgeRecipe(DSRecipes.NETHER_FORGING,id, string, cookingRecipeCategory, ingredient, itemStack, f, i);
        }

        @Override
        public NetherForgeRecipe read(Identifier id, PacketByteBuf buf) {
            String string = buf.readString();
            CookingRecipeCategory cookingRecipeCategory = buf.readEnumConstant(CookingRecipeCategory.class);
            Ingredient ingredient = Ingredient.fromPacket(buf);
            ItemStack itemStack = buf.readItemStack();
            float f = buf.readFloat();
            int i = buf.readVarInt();
            return new NetherForgeRecipe(DSRecipes.NETHER_FORGING,id, string, cookingRecipeCategory, ingredient, itemStack, f, i);
        }

        @Override
        public void write(PacketByteBuf buf, NetherForgeRecipe recipe) {
            buf.writeString(recipe.group);
            recipe.input.write(buf);
            buf.writeItemStack(recipe.output);
            buf.writeFloat(recipe.experience);
            buf.writeVarInt(recipe.cookTime);
        }
    }
}

