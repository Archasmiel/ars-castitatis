package net.archasmiel.ars_castitatis.tags;

import net.archasmiel.ars_castitatis.ArsCastitatisMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static <T> TagKey<T> createTag(String name, Class<T> clas) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(ArsCastitatisMod.MOD_ID, name);

        if (Item.class.isAssignableFrom(clas)) {
            @SuppressWarnings("unchecked")
            TagKey<T> key = (TagKey<T>) ItemTags.create(id);
            return key;
        } else if (Block.class.isAssignableFrom(clas)) {
            @SuppressWarnings("unchecked")
            TagKey<T> key = (TagKey<T>) BlockTags.create(id);
            return key;
        }

        throw new IllegalArgumentException("Unsupported tag type: " + clas.getName());
    }

    public static class Items {

        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return ModTags.createTag(name, Item.class);
        }

    }

    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return ModTags.createTag(name, Block.class);
        }

    }

}
