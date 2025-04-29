package net.archasmiel.ars_castitatis.datagen;

import net.archasmiel.ars_castitatis.block.ModBlocks;
import net.archasmiel.ars_castitatis.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.stream.Stream;


public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output, String modId) {
        super(output, modId);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.createTrivialCube(ModBlocks.REINFORCED_DIORITE.get());
        blockModels.createTrivialCube(ModBlocks.PAPER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.MAGIC_BRICKS.get());

        itemModels.generateFlatItem(ModItems.ARDUINO_UNO.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BATTERY_LIME.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PAPER_SHEETS.get(), ModelTemplates.FLAT_ITEM);
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream();
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream();
    }

}
