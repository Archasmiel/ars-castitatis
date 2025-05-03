package net.archasmiel.arscastitatis.item.custom;

import java.util.List;
import java.util.Map;
import net.archasmiel.arscastitatis.block.ModBlocks;
import net.archasmiel.arscastitatis.component.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

/** Class representing board-like item. */
public class BoardItem extends Item {

  private static final Map<Block, Block> MAPPER =
      Map.of(
          ModBlocks.REINFORCED_DIORITE.get(),
          Blocks.OBSIDIAN,
          Blocks.OBSIDIAN,
          ModBlocks.REINFORCED_DIORITE.get());

  /**
   * Item constructor, specifically considering item will have stack size of 1.
   *
   * @param properties additional item properties
   */
  public BoardItem(Properties properties) {
    super(properties.stacksTo(1));
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level level = context.getLevel();
    if (level.isClientSide()) {
      return InteractionResult.FAIL;
    }

    Block block = level.getBlockState(context.getClickedPos()).getBlock();
    if (MAPPER.containsKey(block)) {
      context.getItemInHand().set(ModDataComponents.COORDINATES, context.getClickedPos());
      level.setBlockAndUpdate(context.getClickedPos(), MAPPER.get(block).defaultBlockState());
      level.playSound(null, context.getClickedPos(), SoundEvents.ANVIL_LAND, SoundSource.BLOCKS);
      return InteractionResult.SUCCESS;
    }

    return InteractionResult.PASS;
  }

  @Override
  public void appendHoverText(
      ItemStack stack,
      TooltipContext context,
      List<Component> tooltipComponents,
      TooltipFlag tooltipFlag) {
    if (tooltipFlag.hasShiftDown()) {
      String testBoardName = "tooltip.arscastitatis.board.arduino_uno.r3.";
      tooltipComponents.add(
          Component.translatable(testBoardName + "title")
              .withStyle(ChatFormatting.UNDERLINE)
              .withStyle(ChatFormatting.AQUA));
      tooltipComponents.add(
          Component.translatable(testBoardName + "type")
              .withStyle(ChatFormatting.ITALIC)
              .withStyle(ChatFormatting.WHITE));
      tooltipComponents.add(
          Component.translatable(testBoardName + "rev").withStyle(ChatFormatting.GREEN));
    } else if (tooltipFlag.hasControlDown()) {
      BlockPos pos = stack.get(ModDataComponents.COORDINATES);
      String show = "tooltip.arscastitatis.board.last_click";
      tooltipComponents.add(Component.translatable(show).withStyle(ChatFormatting.AQUA));
      tooltipComponents.add(
          Component.literal(pos == null ? "null" : pos.toString()).withStyle(ChatFormatting.BOLD));
    } else {
      String show = "tooltip.arscastitatis.board.show_tooltip";
      tooltipComponents.add(Component.translatable(show + "1").withStyle(ChatFormatting.GOLD));
      tooltipComponents.add(Component.translatable(show + "2").withStyle(ChatFormatting.GOLD));
    }
  }
}
