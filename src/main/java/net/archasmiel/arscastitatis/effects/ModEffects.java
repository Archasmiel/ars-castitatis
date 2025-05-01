package net.archasmiel.arscastitatis.effects;

import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.archasmiel.arscastitatis.effects.foodproperties.BatteryLimeEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Class with mod effects. */
public class ModEffects {

  public static final DeferredRegister<MobEffect> MOB_EFFECTS =
      DeferredRegister.create(Registries.MOB_EFFECT, ArsCastitatisMod.MOD_ID);

  public static final DeferredHolder<MobEffect, MobEffect> BATTERY_LIME_EFFECT =
      MOB_EFFECTS.register("battery_lime_effect", BatteryLimeEffect::new);

  /**
   * Adding registry into a {@link IEventBus}.
   *
   * @param bus mod event bus
   */
  public static void register(IEventBus bus) {
    MOB_EFFECTS.register(bus);
  }
}
