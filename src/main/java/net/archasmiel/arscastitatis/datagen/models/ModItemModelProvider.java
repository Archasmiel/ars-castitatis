package net.archasmiel.arscastitatis.datagen.models;

import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.archasmiel.arscastitatis.block.ModBlocks;
import net.archasmiel.arscastitatis.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Mod item model provider.
 *
 * <p>Generates item models for the mod's items.
 */
public class ModItemModelProvider extends ItemModelProvider {

  /**
   * Provider constructor.
   *
   * @param output output from {@link GatherDataEvent}
   * @param helper helper for existing files from {@link GatherDataEvent}
   */
  public ModItemModelProvider(PackOutput output, ExistingFileHelper helper) {
    super(output, ArsCastitatisMod.MOD_ID, helper);
  }

  @Override
  protected void registerModels() {
    basicItem(ModItems.ARDUINO_UNO.get());
    basicItem(ModItems.PAPER_SHEETS.get());
    basicItem(ModItems.BATTERY_LIME.get());

    buttonItem(ModBlocks.PLASTIC_BUTTON, ModBlocks.PLASTIC_BLOCK);
    fenceItem(ModBlocks.PLASTIC_FENCE, ModBlocks.PLASTIC_BLOCK);
    wallItem(ModBlocks.PLASTIC_WALL, ModBlocks.PLASTIC_BLOCK);
    basicItem(ModBlocks.PLASTIC_DOOR.get().asItem());
  }

  public void buttonItem(DeferredHolder<Block, ? extends Block> block,
      DeferredHolder<Block, ? extends Block> baseBlock) {
    this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
        .texture("texture",  ResourceLocation.fromNamespaceAndPath(ArsCastitatisMod.MOD_ID,
            "block/" + baseBlock.getId().getPath()));
  }

  public void fenceItem(DeferredHolder<Block, ? extends Block> block,
      DeferredHolder<Block, ? extends Block> baseBlock) {
    this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
        .texture("texture",  ResourceLocation.fromNamespaceAndPath(ArsCastitatisMod.MOD_ID,
            "block/" + baseBlock.getId().getPath()));
  }

  public void wallItem(DeferredHolder<Block, ? extends Block> block,
      DeferredHolder<Block, ? extends Block> baseBlock) {
    this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
        .texture("wall",  ResourceLocation.fromNamespaceAndPath(ArsCastitatisMod.MOD_ID,
            "block/" + baseBlock.getId().getPath()));
  }

}
