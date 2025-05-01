package net.archasmiel.arscastitatis.effects.foodproperties;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

/** Battery Lime consume effects. */
public class BatteryLimeEffect extends MobEffect {

  /** Effect constructor. */
  public BatteryLimeEffect() {
    super(MobEffectCategory.HARMFUL, 8080072);
  }

  @Override
  public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
    if (livingEntity instanceof ServerPlayer serverPlayer) {
      MobEffectInstance currentEffect = livingEntity.getEffect(MobEffects.BAD_OMEN);

      if (currentEffect == null) {
        serverPlayer.addEffect(new MobEffectInstance(MobEffects.BAD_OMEN, 600, 1));
      } else {
        int ampl = currentEffect.getAmplifier();
        if (ampl > 3) {
          serverPlayer.kill();
        } else {
          serverPlayer.removeEffect(MobEffects.BAD_OMEN);
          serverPlayer.addEffect(new MobEffectInstance(MobEffects.BAD_OMEN, 600, ampl + 1));
        }
      }
    }
    super.onEffectAdded(livingEntity, amplifier);
  }
}
