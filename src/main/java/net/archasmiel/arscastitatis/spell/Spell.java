package net.archasmiel.arscastitatis.spell;

import net.minecraft.resources.ResourceLocation;

/** Class representing a spell. */
public class Spell {

  /** Enum representing spell types. */
  public enum SpellType {
    FIRE,
    ICE
  }

  private final String name;
  private final SpellType type;

  /**
   * Spell constructor.
   *
   * @param location resource location of the spell
   * @param type type of the spell
   */
  public Spell(ResourceLocation location, SpellType type) {
    this.name = location.getPath();
    this.type = type;
  }
}
