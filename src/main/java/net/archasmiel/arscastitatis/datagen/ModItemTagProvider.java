package net.archasmiel.arscastitatis.datagen;

import java.util.concurrent.CompletableFuture;
import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.archasmiel.arscastitatis.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.data.event.GatherDataEvent;

/** Mod item tag provider. */
public class ModItemTagProvider extends ItemTagsProvider {

  /**
   * Provider constructor.
   *
   * @param output output from {@link GatherDataEvent}
   * @param lookupProvider provider from {@link GatherDataEvent}
   * @param blockTags block tags from {@link GatherDataEvent}, pre-registered before this provider
   */
  public ModItemTagProvider(
      PackOutput output,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      CompletableFuture<TagLookup<Block>> blockTags) {
    super(output, lookupProvider, blockTags, ArsCastitatisMod.MOD_ID);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    tag(ModTags.Items.TRANSFORMABLE_ITEMS)
        .add(Items.DIAMOND)
        .add(Items.GOLD_INGOT)
        .add(Items.COPPER_INGOT);
  }
}
