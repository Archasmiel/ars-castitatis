package net.archasmiel.arscastitatis.item.foodproperties;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

/** Battery Lime consume effects. */
public class BatteryConsumeEffect implements ConsumeEffect {

  @Override
  public Type<? extends ConsumeEffect> getType() {
    return Type.APPLY_EFFECTS;
  }

  @Override
  public boolean apply(Level level, ItemStack itemStack, LivingEntity livingEntity) {
    if (!(level instanceof ServerLevel serverLevel)) {
      return false;
    }

    MobEffectInstance currentEffect = livingEntity.getEffect(MobEffects.BAD_OMEN);

    if (currentEffect == null) {
      livingEntity.addEffect(new MobEffectInstance(MobEffects.BAD_OMEN, 600, 1));
    } else {
      int amplifier = currentEffect.getAmplifier();
      if (amplifier > 3) {
        livingEntity.kill(serverLevel);
      } else {
        livingEntity.removeEffect(MobEffects.BAD_OMEN);
        livingEntity.addEffect(new MobEffectInstance(MobEffects.BAD_OMEN, 600, amplifier + 1));
      }
    }

    return true;
  }
}
