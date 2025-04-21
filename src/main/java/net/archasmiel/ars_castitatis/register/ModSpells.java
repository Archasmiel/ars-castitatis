package net.archasmiel.ars_castitatis.register;

import net.archasmiel.ars_castitatis.ArsCastitatisMod;
import net.archasmiel.ars_castitatis.ModRegistries;
import net.archasmiel.ars_castitatis.spell.Spell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSpells {

    /* USING REGISTRY */
    public static final DeferredRegister<Spell> SPELLS = DeferredRegister.create(
        ModRegistries.SPELL_REGISTRY, ArsCastitatisMod.MOD_ID
    );

    public static DeferredHolder<Spell, Spell> SPELL_FIREBALL = SPELLS.register(
        "fireball",
        location -> new Spell(location, Spell.SpellType.FIRE)
    );

    public static void register(IEventBus bus) {
        SPELLS.register(bus);
    }

}
