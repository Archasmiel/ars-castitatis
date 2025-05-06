package net.archasmiel.arscastitatis.datagen.models;

import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.archasmiel.arscastitatis.block.ModBlocks;
import net.archasmiel.arscastitatis.block.custom.LedLampBlock;
import net.archasmiel.arscastitatis.block.custom.RgbLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Mod block state provider.
 *
 * <p>Generates block states and models for the mod's blocks.
 */
public class ModBlockStateProvider extends BlockStateProvider {

  /**
   * Provider constructor.
   *
   * @param output output from {@link GatherDataEvent}
   * @param helper helper for existing files from {@link GatherDataEvent}
   */
  public ModBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
    super(output, ArsCastitatisMod.MOD_ID, helper);
  }

  @Override
  protected void registerStatesAndModels() {
    blockWithItem(ModBlocks.REINFORCED_DIORITE);
    blockWithItem(ModBlocks.PAPER_BLOCK);
    blockWithItem(ModBlocks.MAGIC_BRICKS);
    blockWithItem(ModBlocks.PLASTIC_BLOCK);

    Block plasticBlock = ModBlocks.PLASTIC_BLOCK.get();
    stairsBlock(ModBlocks.PLASTIC_STAIRS.get(), blockTexture(plasticBlock));
    slabBlock(ModBlocks.PLASTIC_SLAB.get(), blockTexture(plasticBlock), blockTexture(plasticBlock));
    buttonBlock(ModBlocks.PLASTIC_BUTTON.get(), blockTexture(plasticBlock));
    pressurePlateBlock(ModBlocks.PLASTIC_PRESSURE_PLATE.get(), blockTexture(plasticBlock));
    fenceBlock(ModBlocks.PLASTIC_FENCE.get(), blockTexture(plasticBlock));
    fenceGateBlock(ModBlocks.PLASTIC_FENCE_GATE.get(), blockTexture(plasticBlock));
    wallBlock(ModBlocks.PLASTIC_WALL.get(), blockTexture(plasticBlock));

    doorBlockWithRenderType(
        ModBlocks.PLASTIC_DOOR.get(),
        modLoc("block/plastic_door_bottom"),
        modLoc("block/plastic_door_top"),
        "cutout");
    trapdoorBlockWithRenderType(
        ModBlocks.PLASTIC_TRAPDOOR.get(), modLoc("block/plastic_trapdoor"), true, "cutout");

    blockItem(ModBlocks.PLASTIC_STAIRS);
    blockItem(ModBlocks.PLASTIC_SLAB);
    blockItem(ModBlocks.PLASTIC_PRESSURE_PLATE);
    blockItem(ModBlocks.PLASTIC_FENCE_GATE);
    blockItem(ModBlocks.PLASTIC_TRAPDOOR, "_bottom");

    multiStateLamp(ModBlocks.LED_LAMP, LedLampBlock.STATE);
    multiStateLamp(ModBlocks.RGB_LAMP, RgbLampBlock.STATE);

    blockItem(ModBlocks.WIRING_BAY);
  }

  private void blockWithItem(DeferredHolder<Block, ? extends Block> blockHolder) {
    simpleBlockWithItem(blockHolder.get(), cubeAll(blockHolder.get()));
  }

  private void blockItem(DeferredHolder<Block, ? extends Block> blockHolder) {
    String location =
        String.format("%s:block/%s", ArsCastitatisMod.MOD_ID, blockHolder.getId().getPath());
    simpleBlockItem(blockHolder.get(), new ModelFile.UncheckedModelFile(location));
  }

  private void blockItem(DeferredHolder<Block, ? extends Block> blockHolder, String appendix) {
    String location =
        String.format(
            "%s:block/%s%s", ArsCastitatisMod.MOD_ID, blockHolder.getId().getPath(), appendix);
    simpleBlockItem(blockHolder.get(), new ModelFile.UncheckedModelFile(location));
  }

  private void multiStateLamp(
      DeferredHolder<Block, ? extends Block> blockHolder, IntegerProperty property) {
    String path = blockHolder.getId().getPath();

    getVariantBuilder(blockHolder.get())
        .forAllStates(
            state -> {
              int value = state.getValue(property);
              String name = "%s_%s".formatted(path, value);
              ResourceLocation textureLoc =
                  ResourceLocation.fromNamespaceAndPath(
                      ArsCastitatisMod.MOD_ID, "block/%s_%s".formatted(path, value));
              return new ConfiguredModel[] {
                new ConfiguredModel(models().cubeAll(name, textureLoc))
              };
            });

    String state0 = "%s_0".formatted(path);
    ResourceLocation textureLoc0 =
        ResourceLocation.fromNamespaceAndPath(
            ArsCastitatisMod.MOD_ID, "block/%s_0".formatted(path));
    simpleBlockItem(blockHolder.get(), models().cubeAll(state0, textureLoc0));
  }
}
