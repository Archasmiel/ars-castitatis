package net.archasmiel.ars_castitatis.block;

import net.archasmiel.ars_castitatis.ArsCastitatisMod;
import net.archasmiel.ars_castitatis.block.custom.FuelBlockItem;
import net.archasmiel.ars_castitatis.block.custom.TeleportBlock;
import net.archasmiel.ars_castitatis.item.ModItems;
import net.archasmiel.ars_castitatis.item.custom.FuelItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.BiFunction;
import java.util.function.Function;

import static net.archasmiel.ars_castitatis.item.ModItems.*;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(Registries.BLOCK, ArsCastitatisMod.MOD_ID);

    public static final DeferredHolder<Block, TeleportBlock> REINFORCED_DIORITE = registerBlock(
        "reinforced_diorite", TeleportBlock::new,
        BlockItem::new,
        Blocks.OBSIDIAN.properties(), itemProps());

    public static final DeferredHolder<Block, Block> PAPER_BLOCK = registerBlock(
        "paper_block", Block::new,
        (b, p) -> new FuelBlockItem(b, p, 1200),
        Blocks.BAMBOO_PLANKS.properties(), itemProps());



    public static <T extends Block, F extends BlockItem>
    DeferredHolder<Block, T> registerBlock(
        String name,
        Function<BlockBehaviour.Properties, T> blockFunc,
        BiFunction<T, Item.Properties, F> blockItemFunc,
        BlockBehaviour.Properties blockProps,
        Item.Properties itemProps) {

        var block = BLOCKS.register(name, key ->
            blockFunc.apply(blockProps.setId(ResourceKey.create(Registries.BLOCK, key)))
        );

        ModItems.registerBlockItem(name, blockItemFunc, block, itemProps);
        return block;
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }

}
