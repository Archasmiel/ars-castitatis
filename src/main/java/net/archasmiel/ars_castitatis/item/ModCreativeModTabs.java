package net.archasmiel.ars_castitatis.item;

import net.archasmiel.ars_castitatis.ArsCastitatisMod;
import net.archasmiel.ars_castitatis.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ArsCastitatisMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB =
        TABS.register("main_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.ARDUINO_UNO.get()))
            .title(Component.translatable("creative_tab.ars_castitatis.main_tab"))
            .withLabelColor(1585395)
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.REINFORCED_DIORITE.get());

                output.accept(ModItems.ARDUINO_UNO.get());
                output.accept(ModItems.BATTERY_LIME.get());
                output.accept(ModItems.PAPER_SHEETS.get());
            })
            .build()
        );

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }

}
