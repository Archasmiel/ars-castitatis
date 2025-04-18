package net.archasmiel.arscastitatis.register;

import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.archasmiel.arscastitatis.ModRegistries;
import net.archasmiel.arscastitatis.spell.Spell;
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
