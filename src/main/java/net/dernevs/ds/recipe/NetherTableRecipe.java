package net.dernevs.ds.recipe;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.apache.commons.compress.harmony.pack200.NewAttribute;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class NetherTableRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    final int width;
    final int height;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public NetherTableRecipe(Identifier id, ItemStack output ,int width, int height, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }
        if(recipeItems.get(0).test(inventory.getStack(0)) &&
                recipeItems.get(1).test(inventory.getStack(1)) &&
                recipeItems.get(2).test(inventory.getStack(2)) &&
                recipeItems.get(3).test(inventory.getStack(3)) &&
                recipeItems.get(4).test(inventory.getStack(4)) &&
                recipeItems.get(5).test(inventory.getStack(5)) &&
                recipeItems.get(6).test(inventory.getStack(6)) &&
                recipeItems.get(7).test(inventory.getStack(7))
        ) {
            return recipeItems.get(8).test(inventory.getStack(8));
        }

        return false;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output.copy();
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
        return Type.INSTANCE;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return this.recipeItems;
    }
    public static class Type implements RecipeType<NetherTableRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "nether_tabling";
    }
    static Map<String, Ingredient> readSymbols(JsonObject json) {
        HashMap<String, Ingredient> map = Maps.newHashMap();
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }
            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }
            map.put(entry.getKey(), Ingredient.fromJson(entry.getValue(), false));
        }
        map.put(" ", Ingredient.EMPTY);
        return map;
    }
    static String[] getPattern(JsonArray json) {
        String[] strings = new String[json.size()];
        if (strings.length > 3) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum");
        }
        if (strings.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        }
        for (int i = 0; i < strings.length; ++i) {
            String string = JsonHelper.asString(json.get(i), "pattern[" + i + "]");
            if (string.length() > 3) {
                throw new JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum");
            }
            if (i > 0 && strings[0].length() != string.length()) {
                throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
            }
            strings[i] = string;
        }
        return strings;
    }
    static String[] removePadding(String ... pattern) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;
        for (int m = 0; m < pattern.length; ++m) {
            String string = pattern[m];
            i = Math.min(i, NetherTableRecipe.findFirstSymbol(string));
            int n = NetherTableRecipe.findLastSymbol(string);
            j = Math.max(j, n);
            if (n < 0) {
                if (k == m) {
                    ++k;
                }
                ++l;
                continue;
            }
            l = 0;
        }
        if (pattern.length == l) {
            return new String[0];
        }
        String[] strings = new String[pattern.length - l - k];
        for (int o = 0; o < strings.length; ++o) {
            strings[o] = pattern[o + k].substring(i, j + 1);
        }
        return strings;
    }
    private static int findFirstSymbol(String line) {
        int i;
        for (i = 0; i < line.length() && line.charAt(i) == ' '; ++i) {
        }
        return i;
    }

    private static int findLastSymbol(String pattern) {
        int i;
        for (i = pattern.length() - 1; i >= 0 && pattern.charAt(i) == ' '; --i) {
        }
        return i;
    }
    static DefaultedList<Ingredient> createPatternMatrix(String[] pattern, Map<String, Ingredient> symbols, int width, int height) {
        DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(width * height, Ingredient.EMPTY);
        HashSet<String> set = Sets.newHashSet(symbols.keySet());
        set.remove(" ");
        for (int i = 0; i < pattern.length; ++i) {
            for (int j = 0; j < pattern[i].length(); ++j) {
                String string = pattern[i].substring(j, j + 1);
                Ingredient ingredient = symbols.get(string);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + string + "' but it's not defined in the key");
                }
                set.remove(string);
                defaultedList.set(j + width * i, ingredient);
            }
        }
        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        }
        return defaultedList;
    }
    public static class Serializer implements RecipeSerializer<NetherTableRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "nether_tabling";
        // this is the name given in the json file

        @Override
        public NetherTableRecipe read(Identifier id, JsonObject json) { //reads in the Json file
            Map<String, Ingredient> map = NetherTableRecipe.readSymbols(JsonHelper.getObject(json, "key"));
            String[] strings = NetherTableRecipe.removePadding(NetherTableRecipe.getPattern(JsonHelper.getArray(json, "pattern")));
            int i = strings[0].length();
            int j = strings.length;
            DefaultedList<Ingredient> defaultedList = NetherTableRecipe.createPatternMatrix(strings, map, i, j);
            ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));

            return new NetherTableRecipe(id, itemStack, i, j, defaultedList);
        }

        @Override
        public NetherTableRecipe read(Identifier id, PacketByteBuf buf) {
            int i = buf.readVarInt();
            int j = buf.readVarInt();
            DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i * j, Ingredient.EMPTY);
            for (int k = 0; k < defaultedList.size(); ++k) {
                defaultedList.set(k, Ingredient.fromPacket(buf));
            }
            ItemStack itemStack = buf.readItemStack();
            return new NetherTableRecipe(id, itemStack,i, j, defaultedList);
        }

        @Override
        public void write(PacketByteBuf buf, NetherTableRecipe recipe) {
            buf.writeVarInt(recipe.width);
            buf.writeVarInt(recipe.height);
            for (Ingredient ingredient : recipe.recipeItems) {
                ingredient.write(buf);
            }
            buf.writeItemStack(recipe.output);
        }
    }
}
