package net.archasmiel.ars_castitatis.datagen;

import net.archasmiel.ars_castitatis.block.ModBlocks;
import net.archasmiel.ars_castitatis.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

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

    public static class Runner extends RecipeProvider.Runner {

        protected Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Ars Castitatis Recipes";
        }

    }

}
