package net.archasmiel.arscastitatis.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.archasmiel.arscastitatis.datagen.models.ModBlockStateProvider;
import net.archasmiel.arscastitatis.datagen.models.ModItemModelProvider;
import net.archasmiel.arscastitatis.datagen.tags.ModBlockTagProvider;
import net.archasmiel.arscastitatis.datagen.tags.ModItemTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

/** Mod data generation class. */
@EventBusSubscriber(modid = ArsCastitatisMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

  /**
   * Client-specific data generation event method.
   *
   * @param event {@link GatherDataEvent} event
   */
  @SubscribeEvent
  public static void gatherDataClient(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    ExistingFileHelper helper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    // Loot tables
    generator.addProvider(
        event.includeServer(),
        new LootTableProvider(
            packOutput,
            Collections.emptySet(),
            List.of(
                new LootTableProvider.SubProviderEntry(
                    ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
            lookupProvider));

    // Recipes
    generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

    // Item and block tags
    BlockTagsProvider blockTagsProvider =
        new ModBlockTagProvider(packOutput, lookupProvider, helper);
    generator.addProvider(event.includeServer(), blockTagsProvider);
    ItemTagsProvider itemTagsProvider =
        new ModItemTagProvider(
            packOutput, lookupProvider, blockTagsProvider.contentsGetter(), helper);
    generator.addProvider(event.includeServer(), itemTagsProvider);

    // Data maps
    generator.addProvider(
        event.includeServer(), new ModDataMapProvider(packOutput, lookupProvider));

    // Models and block states
    generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, helper));
    generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, helper));
  }
}
