package net.archasmiel.arscastitatis.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.FuelValues;
import org.jetbrains.annotations.Nullable;

/** Class representing item with fuel burn time. */
public class FuelItem extends Item {

  private final int burnTime;

  /**
   * Fuel item constructor.
   *
   * @param properties additional item properties
   * @param burnTime burn time in ticks (20 ticks = 1 second, by standard)
   */
  public FuelItem(Properties properties, int burnTime) {
    super(properties);
    this.burnTime = burnTime;
  }

  @Override
  public int getBurnTime(
      ItemStack itemStack, @Nullable RecipeType<?> recipeType, FuelValues fuelValues) {
    return burnTime;
  }
}
