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
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

/**
 * Class representing LED lamp block.
 *
 * <p>LED lamp block is a block that can be clicked to toggle its light level.
 */
public class LedLampBlock extends Block {

  public static final IntegerProperty STATE = IntegerProperty.create("state", 0, 1);

  /**
   * Constructor for LED lamp block.
   *
   * @param properties the properties of the block, lightLevel is already included
   * @param lightLevel the light level to be emitted when clicked
   */
  public LedLampBlock(BlockBehaviour.Properties properties, int lightLevel) {
    super(properties.lightLevel(state -> state.getValue(STATE) == 1 ? lightLevel : 0));
    this.registerDefaultState(this.defaultBlockState().setValue(STATE, 0));
  }

  @Override
  protected InteractionResult useWithoutItem(
      BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    if (level.isClientSide()) {
      return InteractionResult.FAIL;
    }

    int currentState = state.getValue(STATE);
    int newState = (currentState + 1) % 2; // Cycle through colors 0, 1, 2
    level.setBlockAndUpdate(pos, state.setValue(STATE, newState));
    level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS);

    return InteractionResult.SUCCESS;
  }

  @Override
  protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
    builder.add(STATE);
  }
}
