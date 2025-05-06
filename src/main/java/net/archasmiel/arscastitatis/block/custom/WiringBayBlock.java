package net.archasmiel.arscastitatis.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/** Wiring bay block. */
public class WiringBayBlock extends Block {

  /**
   * Block constructor.
   *
   * @param properties block properties
   */
  public WiringBayBlock(Properties properties) {
    super(properties);
  }

  @Override
  protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
      Player player, BlockHitResult hitResult) {
    level.playSound(null, pos, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS);
    return InteractionResult.SUCCESS;
  }
}
