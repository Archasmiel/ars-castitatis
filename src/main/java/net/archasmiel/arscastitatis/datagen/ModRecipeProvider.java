package net.archasmiel.arscastitatis.datagen;

import java.util.concurrent.CompletableFuture;
import net.archasmiel.arscastitatis.block.ModBlocks;
import net.archasmiel.arscastitatis.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.data.event.GatherDataEvent;

/**
 * Mod recipe provider class.
 *
 * <p>Not for adding in events, instead use {@link Runner} subclass.
 */
public class ModRecipeProvider extends RecipeProvider {

  /**
   * Pre-provider constructor.
   *
   * @param registries non{@link CompletableFuture} provider for {@link Runner} class
   * @param output recipe output for {@link Runner} class
   */
  protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
    super(registries, output);
  }

  @Override
  protected void buildRecipes() {
    shaped(RecipeCategory.MISC, ModItems.PAPER_SHEETS.get(), 2)
        .pattern("###")
        .define('#', Items.PAPER)
        .unlockedBy("has_paper_sheets", has(ModItems.PAPER_SHEETS.get()))
        .save(output);

    shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PAPER_BLOCK.get(), 1)
        .pattern("###")
        .pattern("###")
        .pattern("###")
        .define('#', Items.PAPER)
        .unlockedBy("has_paper_sheets", has(ModItems.PAPER_SHEETS.get()))
        .save(output);

    shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REINFORCED_DIORITE.get(), 2)
        .requires(Items.DIORITE)
        .requires(Items.OBSIDIAN)
        .unlockedBy("has_diorite", has(Items.DIORITE))
        .save(output);
  }

  /**
   * Runner class for the mod recipe provider.
   *
   * <p>Use this instead of {@link RecipeProvider} class in events.
   */
  public static class Runner extends RecipeProvider.Runner {

    /**
     * Provider constructor.
     *
     * @param packOutput output from {@link GatherDataEvent}
     * @param registries provider from {@link GatherDataEvent}
     */
    protected Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
      super(packOutput, registries);
    }

    @Override
    protected RecipeProvider createRecipeProvider(
        HolderLookup.Provider provider, RecipeOutput recipeOutput) {
      return new ModRecipeProvider(provider, recipeOutput);
    }

    @Override
    public String getName() {
      return "Ars Castitatis Recipes";
    }
  }
}
