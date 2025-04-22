package net.archasmiel.ars_castitatis.spell;

import net.archasmiel.ars_castitatis.ArsCastitatisMod;
import net.archasmiel.ars_castitatis.ModRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSpells {

    /* USING REGISTRY */
    public static final DeferredRegister<Spell> SPELLS = DeferredRegister.create(
        ModRegistries.SPELL_REGISTRY, ArsCastitatisMod.MOD_ID
    );

    public static DeferredHolder<Spell, Spell> SPELL_FIREBALL = SPELLS.register(
        "fireball", regName -> new Spell(regName, Spell.SpellType.FIRE)
    );

    public static void register(IEventBus bus) {
        SPELLS.register(bus);
    }

}
