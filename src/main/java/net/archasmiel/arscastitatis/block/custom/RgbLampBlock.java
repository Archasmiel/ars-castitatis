package net.archasmiel.arscastitatis.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

/** Class representing RGB lamp block. */
public class RgbLampBlock extends Block {

  public static final IntegerProperty STATE = IntegerProperty.create("color", 0, 3);

  /**
   * Constructor for RGB lamp block.
   *
   * @param properties the properties of the block, lightLevel is already included
   */
  public RgbLampBlock(BlockBehaviour.Properties properties) {
    super(
        properties
            .lightLevel(state -> state.getValue(STATE) > 0 ? 15 : 0)
            .mapColor(
                blockState -> switch (blockState.getValue(STATE)) {
                  case 0 -> MapColor.COLOR_GRAY;
                  case 1 -> MapColor.COLOR_RED;
                  case 2 -> MapColor.COLOR_GREEN;
                  case 3 -> MapColor.COLOR_BLUE;
                  default -> MapColor.COLOR_BLACK;
                }));
  }

  @Override
  protected InteractionResult useWithoutItem(
      BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    if (level.isClientSide()) {
      return InteractionResult.FAIL;
    }

    int currentColor = state.getValue(STATE);
    int newColor = (currentColor + 1) % 4; // Cycle through colors 0, 1, 2
    level.setBlockAndUpdate(pos, state.setValue(STATE, newColor));
    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS);

    return InteractionResult.SUCCESS;
  }

  @Override
  protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
    builder.add(STATE);
  }
}
