package net.archasmiel.arscastitatis.datagen;

import java.util.concurrent.CompletableFuture;
import net.archasmiel.arscastitatis.block.ModBlocks;
import net.archasmiel.arscastitatis.item.ModItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.data.event.GatherDataEvent;

/**
 * Mod recipe provider class.
 *
 * <p>Generates recipes for the mod.
 */
public class ModRecipeProvider extends RecipeProvider {

  /**
   * Provider constructor.
   *
   * @param registries {@link CompletableFuture} provider from {@link GatherDataEvent}
   * @param output pack output from {@link GatherDataEvent}
   */
  public ModRecipeProvider(PackOutput output, CompletableFuture<Provider> registries) {
    super(output, registries);
  }

  @Override
  protected void buildRecipes(RecipeOutput output) {
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PAPER_SHEETS.get(), 2)
        .pattern("###")
        .define('#', Items.PAPER)
        .unlockedBy("has_paper_sheets", has(ModItems.PAPER_SHEETS.get()))
        .save(output);

    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PAPER_BLOCK.get(), 1)
        .pattern("###")
        .pattern("###")
        .pattern("###")
        .define('#', Items.PAPER)
        .unlockedBy("has_paper_sheets", has(ModItems.PAPER_SHEETS.get()))
        .save(output);

    ShapelessRecipeBuilder.shapeless(
            RecipeCategory.BUILDING_BLOCKS, ModBlocks.REINFORCED_DIORITE.get(), 2)
        .requires(Items.DIORITE)
        .requires(Items.OBSIDIAN)
        .unlockedBy("has_diorite", has(Items.DIORITE))
        .save(output);
  }
}
