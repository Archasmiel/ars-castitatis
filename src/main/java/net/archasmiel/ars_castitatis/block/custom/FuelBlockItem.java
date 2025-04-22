package net.archasmiel.ars_castitatis.block.custom;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.FuelValues;
import org.jetbrains.annotations.Nullable;

public class FuelBlockItem extends BlockItem {

    private final int burnTime;

    public FuelBlockItem(Block block, Properties properties, int burnTime) {
        super(block, properties);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType,
                           FuelValues fuelValues) {
        return burnTime;
    }
}
