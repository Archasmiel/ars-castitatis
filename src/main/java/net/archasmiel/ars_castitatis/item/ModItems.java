package net.archasmiel.ars_castitatis.item;

import com.mojang.datafixers.types.Func;
import net.archasmiel.ars_castitatis.ArsCastitatisMod;
import net.archasmiel.ars_castitatis.block.custom.FuelBlockItem;
import net.archasmiel.ars_castitatis.item.custom.BoardItem;
import net.archasmiel.ars_castitatis.item.custom.FuelItem;
import net.archasmiel.ars_castitatis.item.food_properties.ModFoodProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(Registries.ITEM, ArsCastitatisMod.MOD_ID);



    public static final DeferredHolder<Item, BoardItem> ARDUINO_UNO =
        registerItem("arduino_uno", BoardItem::new, itemProps());

    public static final DeferredHolder<Item, Item> BATTERY_LIME =
        registerItem("battery_lime", Item::new,
            itemProps().stacksTo(4)
                .food(ModFoodProperties.BATTERY_FOOD, ModFoodProperties.BATTERY_CONSUMABLE));

    public static final DeferredHolder<Item, FuelItem> PAPER_SHEETS =
        registerItem("paper_sheets", p -> new FuelItem(p, 200), itemProps());



    public static <T extends Item> DeferredHolder<Item, T> registerItem(
        String name,
        Function<Item.Properties, T> itemFunction,
        Item.Properties properties) {

        return ITEMS.register(name, regName ->
            itemFunction.apply(properties.setId(ResourceKey.create(Registries.ITEM, regName)))
        );
    }

    public static <T extends Block, F extends BlockItem> void registerBlockItem(
        String name,
        BiFunction<T, Item.Properties, F> blockItemFunction,
        DeferredHolder<Block, T> block,
        Item.Properties properties) {

        ITEMS.register(name, regName ->
            blockItemFunction.apply(block.get(),
                properties.setId(ResourceKey.create(Registries.ITEM, regName)))
        );
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

    public static Item.Properties itemProps() {
        return new Item.Properties();
    }
}
