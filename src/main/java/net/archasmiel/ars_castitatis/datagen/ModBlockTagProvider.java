package net.archasmiel.ars_castitatis.datagen;

import net.archasmiel.ars_castitatis.ArsCastitatisMod;
import net.archasmiel.ars_castitatis.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output,
                               CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ArsCastitatisMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .replace(false)
            .add(ModBlocks.REINFORCED_DIORITE.get())
            .add(ModBlocks.PAPER_BLOCK.get())
            .add(ModBlocks.MAGIC_BRICKS.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .replace(false)
            .add(ModBlocks.REINFORCED_DIORITE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
            .replace(false)
            .add(ModBlocks.PAPER_BLOCK.get())
            .add(ModBlocks.MAGIC_BRICKS.get());
    }

}
