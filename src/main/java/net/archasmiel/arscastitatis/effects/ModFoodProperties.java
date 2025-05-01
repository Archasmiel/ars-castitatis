package net.archasmiel.arscastitatis.effects;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

/** Class with mod food properties. */
public class ModFoodProperties {

  public static final FoodProperties BATTERY_FOOD =
      new FoodProperties.Builder()
          .nutrition(2)
          .saturationModifier(2f)
          .alwaysEdible()
          .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 600, 2), 1f)
          .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 2), 1f)
          .effect(() -> new MobEffectInstance(ModEffects.BATTERY_LIME_EFFECT, 600, 1), 1f)
          .build();
}
