package net.archasmiel.arscastitatis.datagen.tags;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.archasmiel.arscastitatis.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Mod block tag provider.
 *
 * <p>Generates block tags for the mod.
 */
public class ModBlockTagProvider extends BlockTagsProvider {

  /**
   * Provider constructor.
   *
   * @param output output from {@link GatherDataEvent}
   * @param lookupProvider provider from {@link GatherDataEvent}
   */
  public ModBlockTagProvider(
      PackOutput output,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      ExistingFileHelper helper) {
    super(output, lookupProvider, ArsCastitatisMod.MOD_ID, helper);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    addToTag(
        BlockTags.MINEABLE_WITH_PICKAXE,
        List.of(
            ModBlocks.REINFORCED_DIORITE,
            ModBlocks.PAPER_BLOCK,
            ModBlocks.MAGIC_BRICKS,
            ModBlocks.PLASTIC_BLOCK,
            ModBlocks.PLASTIC_STAIRS,
            ModBlocks.PLASTIC_SLAB,
            ModBlocks.PLASTIC_PRESSURE_PLATE,
            ModBlocks.PLASTIC_BUTTON,
            ModBlocks.PLASTIC_FENCE,
            ModBlocks.PLASTIC_FENCE_GATE,
            ModBlocks.PLASTIC_WALL,
            ModBlocks.PLASTIC_DOOR,
            ModBlocks.PLASTIC_TRAPDOOR,
            ModBlocks.LED_LAMP,
            ModBlocks.RGB_LAMP,
            ModBlocks.WIRING_BAY));

    addToTag(
        BlockTags.MINEABLE_WITH_AXE,
        List.of(
            ModBlocks.PLASTIC_BLOCK,
            ModBlocks.PLASTIC_STAIRS,
            ModBlocks.PLASTIC_SLAB,
            ModBlocks.PLASTIC_PRESSURE_PLATE,
            ModBlocks.PLASTIC_BUTTON,
            ModBlocks.PLASTIC_FENCE,
            ModBlocks.PLASTIC_FENCE_GATE,
            ModBlocks.PLASTIC_WALL,
            ModBlocks.PLASTIC_DOOR,
            ModBlocks.PLASTIC_TRAPDOOR));

    addToTag(
        BlockTags.NEEDS_DIAMOND_TOOL,
        List.of(
            ModBlocks.REINFORCED_DIORITE));

    addToTag(
        BlockTags.NEEDS_IRON_TOOL,
        List.of(
            ModBlocks.PAPER_BLOCK,
            ModBlocks.MAGIC_BRICKS,
            ModBlocks.WIRING_BAY));

    addToTag(
        BlockTags.NEEDS_STONE_TOOL,
        List.of(
            ModBlocks.PLASTIC_BLOCK,
            ModBlocks.PLASTIC_STAIRS,
            ModBlocks.PLASTIC_SLAB,
            ModBlocks.PLASTIC_PRESSURE_PLATE,
            ModBlocks.PLASTIC_BUTTON,
            ModBlocks.PLASTIC_FENCE,
            ModBlocks.PLASTIC_FENCE_GATE,
            ModBlocks.PLASTIC_WALL,
            ModBlocks.PLASTIC_DOOR,
            ModBlocks.PLASTIC_TRAPDOOR,
            ModBlocks.LED_LAMP,
            ModBlocks.RGB_LAMP));

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

  private void addToTag(TagKey<Block> tag, List<DeferredHolder<Block, ? extends Block>> holder) {
    IntrinsicTagAppender<Block> appender = tag(tag).replace(false);
    for (DeferredHolder<Block, ? extends Block> block : holder) {
      appender.add(block.get());
    }
  }
}
