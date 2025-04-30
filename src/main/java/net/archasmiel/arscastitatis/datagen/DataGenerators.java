package net.archasmiel.arscastitatis.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

/** Mod data generation class. */
@EventBusSubscriber(modid = ArsCastitatisMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

  /**
   * Client-specific data generation event method.
   *
   * @param event {@link GatherDataEvent.Client} client event
   */
  @SubscribeEvent
  public static void gatherDataClient(GatherDataEvent.Client event) {
    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    // Loot tables
    generator.addProvider(
        true,
        new LootTableProvider(
            packOutput,
            Collections.emptySet(),
            List.of(
                new LootTableProvider.SubProviderEntry(
                    ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
            lookupProvider));

    // Recipes
    generator.addProvider(true, new ModRecipeProvider.Runner(packOutput, lookupProvider));

    // Tags
    BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider);
    generator.addProvider(true, blockTagsProvider);
    ItemTagsProvider itemTagsProvider =
        new ModItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter());
    generator.addProvider(true, itemTagsProvider);

    // Data map
    generator.addProvider(true, new ModDataMapProvider(packOutput, lookupProvider));

    // Models
    generator.addProvider(true, new ModModelProvider(packOutput, ArsCastitatisMod.MOD_ID));
  }
}
