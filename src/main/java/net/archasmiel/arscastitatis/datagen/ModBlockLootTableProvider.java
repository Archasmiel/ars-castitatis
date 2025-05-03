package net.archasmiel.arscastitatis.datagen;

import java.util.Set;
import net.archasmiel.arscastitatis.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.data.event.GatherDataEvent;

/**
 * Mod loot table provider class.
 *
 * <p>Generates loot tables for the mod's blocks.
 */
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

    dropSelf(ModBlocks.LED_LAMP.get());
    dropSelf(ModBlocks.RGB_LAMP.get());
  }

  /**
   * Creates a loot table for a block that drops itself.
   *
   * @param block the block to drop
   * @param item the item(s) to drop
   * @param minDrops the minimum number of drops
   * @param maxDrops the maximum number of drops
   * @return the loot table builder
   */
  private LootTable.Builder createMultipleOreDrops(
      Block block, Item item, float minDrops, float maxDrops) {
    HolderLookup.RegistryLookup<Enchantment> registrylookup =
        this.registries.lookupOrThrow(Registries.ENCHANTMENT);
    return this.createSilkTouchDispatchTable(
        block,
        this.applyExplosionDecay(
            block,
            LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                .apply(
                    ApplyBonusCount.addOreBonusCount(
                        registrylookup.getOrThrow(Enchantments.FORTUNE)))));
  }

  @Override
  protected Iterable<Block> getKnownBlocks() {
    return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
  }
}
