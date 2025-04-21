package net.archasmiel.ars_castitatis.item;

import net.archasmiel.ars_castitatis.register.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class BoardItem extends Item {

    private static final Map<Block, Block> MAPPER = Map.of(
        ModBlocks.REINFORCED_DIORITE.get(), Blocks.OBSIDIAN,
        Blocks.OBSIDIAN, ModBlocks.REINFORCED_DIORITE.get()
    );

    public BoardItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResult useOn(
                    UseOnContext context) {
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
}
