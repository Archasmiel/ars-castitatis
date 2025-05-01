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
import net.minecraft.world.phys.BlockHitResult;

/**
 * Class representing LED lamp block.
 *
 * <p>LED lamp block is a block that can be clicked to toggle its light level.
 */
public class LedLampBlock extends Block {

  public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");

  /**
   * Constructor for LED lamp block.
   *
   * @param properties the properties of the block, lightLevel is already included
   * @param lightLevel the light level to be emitted when clicked
   */
  public LedLampBlock(BlockBehaviour.Properties properties, int lightLevel) {
    super(properties.lightLevel(state -> state.getValue(CLICKED) ? lightLevel : 0));
    this.registerDefaultState(this.defaultBlockState().setValue(CLICKED, false));
  }

  @Override
  protected InteractionResult useWithoutItem(
      BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    if (level.isClientSide()) {
      return InteractionResult.FAIL;
    }

    boolean currentState = state.getValue(CLICKED);
    level.setBlockAndUpdate(pos, state.setValue(CLICKED, !currentState));
    level.playSound(null, pos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS);

    return InteractionResult.SUCCESS;
  }

  @Override
  protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
    builder.add(CLICKED);
  }
}
