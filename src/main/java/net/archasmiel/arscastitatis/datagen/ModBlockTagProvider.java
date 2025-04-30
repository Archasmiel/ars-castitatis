package net.archasmiel.arscastitatis.datagen;

import java.util.concurrent.CompletableFuture;
import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.archasmiel.arscastitatis.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

/** Mod block tag provider. */
public class ModBlockTagProvider extends BlockTagsProvider {

  /**
   * Provider constructor.
   *
   * @param output output from {@link GatherDataEvent}
   * @param lookupProvider provider from {@link GatherDataEvent}
   */
  public ModBlockTagProvider(
      PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
    super(output, lookupProvider, ArsCastitatisMod.MOD_ID);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .replace(false)
        .add(ModBlocks.REINFORCED_DIORITE.get())
        .add(ModBlocks.PAPER_BLOCK.get())
        .add(ModBlocks.MAGIC_BRICKS.get());

    tag(BlockTags.NEEDS_DIAMOND_TOOL).replace(false).add(ModBlocks.REINFORCED_DIORITE.get());

    tag(BlockTags.NEEDS_IRON_TOOL)
        .replace(false)
        .add(ModBlocks.PAPER_BLOCK.get())
        .add(ModBlocks.MAGIC_BRICKS.get());

    tag(BlockTags.STAIRS).add(ModBlocks.PLASTIC_STAIRS.get());
    tag(BlockTags.SLABS).add(ModBlocks.PLASTIC_SLAB.get());
    tag(BlockTags.BUTTONS).add(ModBlocks.PLASTIC_BUTTON.get());
    tag(BlockTags.PRESSURE_PLATES).add(ModBlocks.PLASTIC_PRESSURE_PLATE.get());
    tag(BlockTags.FENCES).add(ModBlocks.PLASTIC_FENCE.get());
    tag(BlockTags.FENCE_GATES).add(ModBlocks.PLASTIC_FENCE_GATE.get());
    tag(BlockTags.WALLS).add(ModBlocks.PLASTIC_WALL.get());
    tag(BlockTags.DOORS).add(ModBlocks.PLASTIC_DOOR.get());
    tag(BlockTags.TRAPDOORS).add(ModBlocks.PLASTIC_TRAPDOOR.get());
  }
}
