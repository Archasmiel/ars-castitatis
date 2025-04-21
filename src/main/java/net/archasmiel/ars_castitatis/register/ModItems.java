package net.archasmiel.ars_castitatis.register;

import net.archasmiel.ars_castitatis.ArsCastitatisMod;
import net.archasmiel.ars_castitatis.item.BoardItem;
import net.archasmiel.ars_castitatis.item.ModFoodProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(Registries.ITEM, ArsCastitatisMod.MOD_ID);


    public static final DeferredHolder<Item, Item> ARDUINO_UNO =
        registerItem("arduino_uno", BoardItem::new, new Item.Properties());

    public static final DeferredHolder<Item, Item> BATTERY_LIME =
        registerItem("battery_lime", Item::new,
            new Item.Properties().stacksTo(4)
                .food(ModFoodProperties.BATTERY_FOOD, ModFoodProperties.BATTERY_CONSUMABLE));


    public static <T extends Item> DeferredHolder<Item, T> registerItem(String name,
                                                 Function<Item.Properties, T> itemFunction,
                                                 Item.Properties properties) {
        return ITEMS.register(
            name, regName -> itemFunction.apply(
                properties.setId(ResourceKey.create(Registries.ITEM, regName))
            )
        );
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
