package net.archasmiel.arscastitatis.datagen;

import java.util.Set;
import net.archasmiel.arscastitatis.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.data.event.GatherDataEvent;

/** Mod loot table provider class. */
public class ModBlockLootTableProvider extends BlockLootSubProvider {

  /**
   * Provider constructor.
   *
   * @param registries provider from {@link GatherDataEvent}
   */
  protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
    super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
  }

  @Override
  protected void generate() {
    dropSelf(ModBlocks.REINFORCED_DIORITE.get());
    dropSelf(ModBlocks.PAPER_BLOCK.get());
    dropSelf(ModBlocks.MAGIC_BRICKS.get());

    dropSelf(ModBlocks.PLASTIC_BLOCK.get());
    dropSelf(ModBlocks.PLASTIC_STAIRS.get());
    add(ModBlocks.PLASTIC_SLAB.get(), block -> createSlabItemTable(ModBlocks.PLASTIC_SLAB.get()));
    dropSelf(ModBlocks.PLASTIC_PRESSURE_PLATE.get());
    dropSelf(ModBlocks.PLASTIC_BUTTON.get());
    dropSelf(ModBlocks.PLASTIC_FENCE.get());
    dropSelf(ModBlocks.PLASTIC_FENCE_GATE.get());
    dropSelf(ModBlocks.PLASTIC_WALL.get());
    dropSelf(ModBlocks.PLASTIC_TRAPDOOR.get());
    add(ModBlocks.PLASTIC_DOOR.get(), block -> createDoorTable(ModBlocks.PLASTIC_DOOR.get()));
  }

  @Override
  protected Iterable<Block> getKnownBlocks() {
    return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
  }
}
