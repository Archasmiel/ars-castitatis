package net.archasmiel.arscastitatis.register;

import net.archasmiel.arscastitatis.ArsCastitatisMod;
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

import java.util.function.Function;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(Registries.BLOCK, ArsCastitatisMod.MOD_ID);

    public static final DeferredHolder<Block, Block> REINFORCED_DIORITE = registerBlock(
        "reinforced_diorite",
        properties -> new Block(properties),
        Blocks.OBSIDIAN.properties()
    );

    public static <T extends Block> DeferredHolder<Block, T> registerBlock(String name,
                                               Function<BlockBehaviour.Properties, T> blockFunction,
                                               BlockBehaviour.Properties blockProperties) {
        DeferredHolder<Block, T> blockDef = BLOCKS.register(
            name, regName -> blockFunction.apply(
                blockProperties.setId(ResourceKey.create(Registries.BLOCK, regName))
            )
        );
        registerBlockItem(name, blockDef);
        return blockDef;
    }

    public static <T extends Block> void registerBlockItem(String name, DeferredHolder<Block, T> block) {
        ModItems.ITEMS.register(
            name, regName -> new BlockItem(
                block.get(), new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, regName))
            )
        );
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }

}
