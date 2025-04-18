package net.archasmiel.arscastitatis;

import net.archasmiel.arscastitatis.spell.Spell;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

@EventBusSubscriber(modid = ArsCastitatisMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModRegistries {

    /*   SPELLS   */
    public static final ResourceKey<Registry<Spell>> SPELL_REGISTRY_KEY =
        ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(
            ArsCastitatisMod.MOD_ID, "spells"
        ));

    private static final ResourceLocation SPELL_DEFAULT =
        ResourceLocation.fromNamespaceAndPath(ArsCastitatisMod.MOD_ID, "empty");

    public static Registry<Spell> SPELL_REGISTRY = new RegistryBuilder<>(SPELL_REGISTRY_KEY)
        .defaultKey(SPELL_DEFAULT)
        .create();



    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event) {
        event.register(SPELL_REGISTRY);
    }

}
