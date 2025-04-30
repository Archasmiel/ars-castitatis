package net.archasmiel.arscastitatis.datagen;

import java.util.stream.Stream;
import net.archasmiel.arscastitatis.block.ModBlocks;
import net.archasmiel.arscastitatis.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.data.event.GatherDataEvent;

/** Mod block and item model provider class. */
public class ModModelProvider extends ModelProvider {

  /**
   * Provider constructor.
   *
   * @param output output from {@link GatherDataEvent}
   * @param modId mod ID
   */
  public ModModelProvider(PackOutput output, String modId) {
    super(output, modId);
  }

  @Override
  protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
    blockModels.createTrivialCube(ModBlocks.REINFORCED_DIORITE.get());
    blockModels.createTrivialCube(ModBlocks.PAPER_BLOCK.get());
    blockModels.createTrivialCube(ModBlocks.MAGIC_BRICKS.get());

    blockModels.createTrivialCube(ModBlocks.PLASTIC_BLOCK.get());
    blockModels
        .familyWithExistingFullBlock(ModBlocks.PLASTIC_BLOCK.get())
        .stairs(ModBlocks.PLASTIC_STAIRS.get());
    blockModels
        .familyWithExistingFullBlock(ModBlocks.PLASTIC_BLOCK.get())
        .slab(ModBlocks.PLASTIC_SLAB.get());
    blockModels
        .familyWithExistingFullBlock(ModBlocks.PLASTIC_BLOCK.get())
        .button(ModBlocks.PLASTIC_BUTTON.get());
    blockModels
        .familyWithExistingFullBlock(ModBlocks.PLASTIC_BLOCK.get())
        .fence(ModBlocks.PLASTIC_FENCE.get());
    blockModels
        .familyWithExistingFullBlock(ModBlocks.PLASTIC_BLOCK.get())
        .fenceGate(ModBlocks.PLASTIC_FENCE_GATE.get());
    blockModels
        .familyWithExistingFullBlock(ModBlocks.PLASTIC_BLOCK.get())
        .wall(ModBlocks.PLASTIC_WALL.get());
    blockModels
        .familyWithExistingFullBlock(ModBlocks.PLASTIC_BLOCK.get())
        .pressurePlate(ModBlocks.PLASTIC_PRESSURE_PLATE.get());
    blockModels
        .familyWithExistingFullBlock(ModBlocks.PLASTIC_BLOCK.get())
        .door(ModBlocks.PLASTIC_DOOR.get());
    blockModels
        .familyWithExistingFullBlock(ModBlocks.PLASTIC_BLOCK.get())
        .trapdoor(ModBlocks.PLASTIC_TRAPDOOR.get());

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
