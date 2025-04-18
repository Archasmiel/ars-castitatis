package net.archasmiel.arscastitatis.register;

import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(Registries.ITEM, ArsCastitatisMod.MOD_ID);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
