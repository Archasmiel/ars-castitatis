package net.archasmiel.ars_castitatis.item.custom;

import net.archasmiel.ars_castitatis.block.ModBlocks;
import net.archasmiel.ars_castitatis.tags.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.function.Consumer;

public class BoardItem extends Item {

    private static final Map<Block, Block> MAPPER = Map.of(
        ModBlocks.REINFORCED_DIORITE.get(), Blocks.OBSIDIAN,
        Blocks.OBSIDIAN, ModBlocks.REINFORCED_DIORITE.get()
    );

    public BoardItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        Block block = level.getBlockState(context.getClickedPos()).getBlock();
        if (MAPPER.containsKey(block)) {
            level.setBlockAndUpdate(context.getClickedPos(), MAPPER.get(block).defaultBlockState());
            level.playSound(null, context.getClickedPos(), SoundEvents.ANVIL_LAND, SoundSource.BLOCKS);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack item, TooltipContext context,
                                TooltipDisplay display, Consumer<Component> consumer,
                                TooltipFlag flag) {
        String testBoardName = "item.board.arduino.uno.r3.";
        consumer.accept(
            Component.translatable(testBoardName + "title")
                .withStyle(ChatFormatting.UNDERLINE)
                .withStyle(ChatFormatting.AQUA));
        consumer.accept(Component.translatable(testBoardName + "type")
            .withStyle(ChatFormatting.ITALIC)
            .withStyle(ChatFormatting.WHITE));
        consumer.accept(Component.translatable(testBoardName + "rev")
            .withStyle(ChatFormatting.GREEN));
    }
}
