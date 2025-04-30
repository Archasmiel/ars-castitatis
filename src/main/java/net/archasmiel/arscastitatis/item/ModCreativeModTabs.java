package net.archasmiel.arscastitatis.item;

import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.archasmiel.arscastitatis.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Class with mod creative tabs. */
public class ModCreativeModTabs {

  public static final DeferredRegister<CreativeModeTab> TABS =
      DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ArsCastitatisMod.MOD_ID);

  public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB =
      TABS.register(
          "main_tab",
          () ->
              CreativeModeTab.builder()
                  .icon(() -> new ItemStack(ModItems.ARDUINO_UNO.get()))
                  .title(Component.translatable("creative_tab.arscastitatis.main_tab"))
                  .withLabelColor(1585395)
                  .displayItems(
                      (itemDisplayParameters, output) -> {
                        ModBlocks.displayCreativeModTab(itemDisplayParameters, output);
                        ModItems.displayCreativeModTab(itemDisplayParameters, output);
                      })
                  .build());

  /**
   * Adding registry into a {@link IEventBus}.
   *
   * @param bus mod event bus
   */
  public static void register(IEventBus bus) {
    TABS.register(bus);
  }
}
