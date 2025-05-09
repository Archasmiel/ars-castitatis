package net.archasmiel.arscastitatis.block.custom;

import net.archasmiel.arscastitatis.menu.WiringBayMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

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
  protected InteractionResult useWithoutItem(
      BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
      serverPlayer.openMenu(state.getMenuProvider(level, pos));
    }

    level.playSound(null, pos, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS);
    return InteractionResult.sidedSuccess(level.isClientSide());
  }

  @Override
  protected @Nullable MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
    return new SimpleMenuProvider(
        (id, inventory, playerEntity) -> new WiringBayMenu(id, inventory),
        Component.translatable("screen.arscastitatis.wiring_bay.title"));
  }
}
