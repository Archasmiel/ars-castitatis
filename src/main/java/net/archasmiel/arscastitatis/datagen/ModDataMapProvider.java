package net.archasmiel.arscastitatis.datagen;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

/**
 * Mod data map provider class.
 *
 * <p>Generates data maps for the mod.
 */
public class ModDataMapProvider extends DataMapProvider {

  /**
   * Provider constructor.
   *
   * @param output output from {@link GatherDataEvent}
   * @param lookupProvider provider from {@link GatherDataEvent}
   */
  protected ModDataMapProvider(
      PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
    super(output, lookupProvider);
  }

  @Override
  protected void gather(HolderLookup.Provider provider) {}
}
