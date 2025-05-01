package net.archasmiel.arscastitatis.item;

import java.util.function.BiFunction;
import java.util.function.Function;
import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.archasmiel.arscastitatis.effects.ModFoodProperties;
import net.archasmiel.arscastitatis.item.custom.BoardItem;
import net.archasmiel.arscastitatis.item.custom.FuelItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Class with mod items. */
public class ModItems {

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(Registries.ITEM, ArsCastitatisMod.MOD_ID);

  public static final DeferredHolder<Item, BoardItem> ARDUINO_UNO;
  public static final DeferredHolder<Item, Item> BATTERY_LIME;
  public static final DeferredHolder<Item, FuelItem> PAPER_SHEETS;

  /**
   * Registers a custom item in the mod item registry.
   *
   * @param name the name of the item to register
   * @param itemFunction a {@link Function} that creates the item given its properties
   * @param properties the {@link Item.Properties} of the item (e.g., durability, stack size, food
   *     properties)
   * @param <T> the custom item type extending {@link Item}
   * @return a {@link DeferredHolder} representing the registered item
   */
  public static <T extends Item> DeferredHolder<Item, T> registerItem(
      String name, Function<Item.Properties, T> itemFunction, Item.Properties properties) {

    return ITEMS.register(name, regName -> itemFunction.apply(properties));
  }

  /**
   * Registers a custom block item linked to a block.
   *
   * @param name the name of the block item to register
   * @param blockItemFunction a {@link BiFunction} that creates the block item from a block and its
   *     properties
   * @param block the {@link DeferredHolder} of the block this item represents
   * @param properties the {@link Item.Properties} of the block item (e.g., stack size, rarity)
   * @param <T> the custom block type extending {@link Block}
   * @param <F> the custom block item type extending {@link BlockItem}
   */
  public static <T extends Block, F extends BlockItem> void registerBlockItem(
      String name,
      BiFunction<T, Item.Properties, F> blockItemFunction,
      DeferredHolder<Block, T> block,
      Item.Properties properties) {

    ITEMS.register(name, regName -> blockItemFunction.apply(block.get(), properties));
  }

  static {
    ARDUINO_UNO = registerItem("arduino_uno", BoardItem::new, new Item.Properties());
    BATTERY_LIME =
        registerItem(
            "battery_lime",
            Item::new,
            new Item.Properties().stacksTo(1).food(ModFoodProperties.BATTERY_FOOD));
    PAPER_SHEETS = registerItem("paper_sheets", p -> new FuelItem(p, 200), new Item.Properties());
  }

  /**
   * Populates the custom creative mode tab with mod blocks.
   *
   * @param parameters the display parameters provided by {@link CreativeModeTab}
   * @param output the output used to accept blocks into the creative tab
   */
  public static void displayCreativeModTab(
      CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {

    output.accept(ModItems.ARDUINO_UNO.get());
    output.accept(ModItems.BATTERY_LIME.get());
    output.accept(ModItems.PAPER_SHEETS.get());
  }

  /**
   * Adding registry into a {@link IEventBus}.
   *
   * @param bus mod event bus
   */
  public static void register(IEventBus bus) {
    ITEMS.register(bus);
  }
}
