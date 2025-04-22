package net.archasmiel.ars_castitatis.item.food_properties;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class ModFoodProperties {

    public static final FoodProperties BATTERY_FOOD = new FoodProperties.Builder()
        .nutrition(2).saturationModifier(2f)
        .alwaysEdible().build();

    public static final Consumable BATTERY_CONSUMABLE = Consumables.defaultDrink()
        .onConsume(new ApplyStatusEffectsConsumeEffect(
            List.of(
                new MobEffectInstance(MobEffects.HASTE, 600, 2),
                new MobEffectInstance(MobEffects.SPEED, 400, 2)
            )))
        .onConsume(new BatteryConsumeEffect())
        .build();

}
