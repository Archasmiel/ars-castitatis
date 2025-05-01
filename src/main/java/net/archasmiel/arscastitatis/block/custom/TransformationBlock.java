package net.archasmiel.arscastitatis.block.custom;

import java.util.HashMap;
import java.util.Map;
import net.archasmiel.arscastitatis.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

/** Class representing transformation block. */
public class TransformationBlock extends Block {

  private final Map<Item, Item> transforms = new HashMap<>();

  /**
   * Transform block constructor.
   *
   * @param properties additional {@link BlockBehaviour.Properties}
   */
  public TransformationBlock(BlockBehaviour.Properties properties, Map<Item, Item> transforms) {
    super(properties);
    this.transforms.putAll(transforms);
  }

  /**
   * Adds a transformation to the block.
   *
   * @param itemFrom item to be transformed
   * @param itemTo item to transform to
   */
  public void addTransform(Item itemFrom, Item itemTo) {
    if (itemFrom == null || itemTo == null) {
      return;
    }
    if (itemTo.equals(itemFrom)) {
      return;
    }
    transforms.put(itemFrom, itemTo);
  }

  @Override
  public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
    if (entity instanceof ItemEntity itemEntity) {
      if (isValidItem(itemEntity)) {
        ItemStack stack = itemEntity.getItem();
        Item itemTo = transforms.get(stack.getItem());
        itemEntity.setItem(new ItemStack(itemTo, stack.getCount()));
        level.playSound(null, pos, SoundEvents.BLAZE_SHOOT, SoundSource.BLOCKS);
      }
    }
  }

  /**
   * Checks if the item is valid for transformation.
   *
   * @param item item to be checked
   * @return true if the item is valid, false otherwise
   */
  private boolean isValidItem(ItemStack item) {
    return transforms.containsKey(item.getItem()) && item.is(ModTags.Items.TRANSFORMABLE_ITEMS);
  }

  /**
   * Checks if the item entity is valid for transformation.
   *
   * @param item item entity to be checked
   * @return true if the item entity is valid, false otherwise
   */
  private boolean isValidItem(ItemEntity item) {
    return isValidItem(item.getItem());
  }
}
