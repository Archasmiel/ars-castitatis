package net.archasmiel.arscastitatis.block.custom;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.FuelValues;
import org.jetbrains.annotations.Nullable;

/** Class representing block item with fuel burn time. */
public class FuelBlockItem extends BlockItem {

  private final int burnTime;

  /**
   * Fuel block item constructor.
   *
   * @param block {@link Block} to be used as item
   * @param properties additional {@link Item.Properties}
   * @param burnTime burn time in ticks (20 ticks = 1 second, by standard)
   */
  public FuelBlockItem(Block block, Properties properties, int burnTime) {
    super(block, properties);
    this.burnTime = burnTime;
  }

  @Override
  public int getBurnTime(
      ItemStack itemStack, @Nullable RecipeType<?> recipeType, FuelValues fuelValues) {
    return burnTime;
  }
}
