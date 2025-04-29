package net.archasmiel.ars_castitatis.datagen;

import net.archasmiel.ars_castitatis.ArsCastitatisMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, ArsCastitatisMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }

}
