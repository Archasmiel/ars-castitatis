package net.archasmiel.arscastitatis.spell;

import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.archasmiel.arscastitatis.ModRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Class with mod spells. */
public class ModSpells {

  public static final DeferredRegister<Spell> SPELLS =
      DeferredRegister.create(ModRegistries.SPELL_REGISTRY, ArsCastitatisMod.MOD_ID);

  public static DeferredHolder<Spell, Spell> SPELL_FIREBALL =
      SPELLS.register("fireball", regName -> new Spell(regName, Spell.SpellType.FIRE));

  /**
   * Adding registry into a {@link IEventBus}.
   *
   * @param bus mod event bus
   */
  public static void register(IEventBus bus) {
    SPELLS.register(bus);
  }
}
