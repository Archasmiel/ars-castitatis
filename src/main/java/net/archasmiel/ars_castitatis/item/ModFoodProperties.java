package net.archasmiel.ars_castitatis.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

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
        .onConsume(new ConsumeEffect() {
            @Override
            public Type<? extends ConsumeEffect> getType() {
                return Type.APPLY_EFFECTS;
            }

            @Override
            public boolean apply(Level level, ItemStack itemStack, LivingEntity livingEntity) {
                if (level instanceof ServerLevel serverLevel) {
                    if (!livingEntity.hasEffect(MobEffects.BAD_OMEN)){
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.BAD_OMEN, 600, 1));
                        return true;
                    }
                    MobEffectInstance brainDamage = livingEntity.getEffect(MobEffects.BAD_OMEN);
                    int amplifier = brainDamage.getAmplifier();
                    if (amplifier > 3) {
                        livingEntity.kill(serverLevel);
                    }
                    livingEntity.removeEffect(MobEffects.BAD_OMEN);
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.BAD_OMEN, 600, amplifier+1));
                    return true;
                }
                return false;
            }
        })
        .build();

}
