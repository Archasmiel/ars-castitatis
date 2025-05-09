package net.archasmiel.arscastitatis.menu;

import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Mod menus. */
public class ModMenus {

  public static final DeferredRegister<MenuType<?>> MENUS =
      DeferredRegister.create(Registries.MENU, "arscastitatis");

  public static final Supplier<MenuType<WiringBayMenu>> WIRING_BAY =
      MENUS.register(
          "wiring_bay", () -> new MenuType<>(WiringBayMenu::new, FeatureFlags.DEFAULT_FLAGS));

  /**
   * Adding registry into a {@link IEventBus}.
   *
   * @param bus mod event bus
   */
  public static void register(IEventBus bus) {
    MENUS.register(bus);
  }
}
