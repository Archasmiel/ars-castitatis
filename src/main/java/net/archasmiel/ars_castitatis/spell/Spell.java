package net.archasmiel.ars_castitatis.spell;

import net.minecraft.resources.ResourceLocation;

public class Spell {

    public enum SpellType {
        FIRE,
        ICE
    }

    private final String name;
    private final SpellType type;

    public Spell(ResourceLocation location, SpellType type) {
        this.name = location.getPath();
        this.type = type;
    }

}
