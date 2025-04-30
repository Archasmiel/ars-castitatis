package net.archasmiel.arscastitatis.tags;

import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/** Class with mod tags. */
public class ModTags {

  /**
   * Creates a tag key for the given name and class.
   *
   * @param name name of the tag
   * @param clas {@link Class} of the tag
   * @param <T> the type of the tag, must be {@link Item}, {@link Block} or their subclasses
   * @return a {@link TagKey} for the given name and class
   */
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

  /** All mod item tags. */
  public static class Items {

    public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

    private static TagKey<Item> createTag(String name) {
      return ModTags.createTag(name, Item.class);
    }
  }

  /** All mod block tags. */
  public static class Blocks {

    private static TagKey<Block> createTag(String name) {
      return ModTags.createTag(name, Block.class);
    }
  }
}
