package net.archasmiel.arscastitatis;

import com.mojang.logging.LogUtils;
import net.archasmiel.arscastitatis.block.ModBlocks;
import net.archasmiel.arscastitatis.component.ModDataComponents;
import net.archasmiel.arscastitatis.effects.ModEffects;
import net.archasmiel.arscastitatis.item.ModCreativeModTabs;
import net.archasmiel.arscastitatis.item.ModItems;
import net.archasmiel.arscastitatis.spell.ModSpells;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

/** Ars Castitatis mod main class. */
@Mod(ArsCastitatisMod.MOD_ID)
public class ArsCastitatisMod {

  /** Mod ID). */
  public static final String MOD_ID = "arscastitatis";

  public static final Logger LOGGER = LogUtils.getLogger();

  /** Mod configuration. */
  public ArsCastitatisMod(IEventBus modEventBus, ModContainer modContainer) {
    modEventBus.addListener(this::commonSetup);
    NeoForge.EVENT_BUS.register(this);

    ModCreativeModTabs.register(modEventBus);
    ModEffects.register(modEventBus);
    ModItems.register(modEventBus);
    ModBlocks.register(modEventBus);
    ModSpells.register(modEventBus);

    ModDataComponents.register(modEventBus);

    modEventBus.addListener(this::addCreative);
    modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {}

  private void addCreative(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
      event.accept(ModBlocks.REINFORCED_DIORITE.get());
    }
  }

  /**
   * Server starting event.
   */
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    LOGGER.info("NPCs enabled: {}", event.getServer().areNpcsEnabled());
  }


  /** Client mod events. */
  @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
  public static class ClientModEvents {

    /** Client setup event. */
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

    }
  }
}
