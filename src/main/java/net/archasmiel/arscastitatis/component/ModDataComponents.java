package net.archasmiel.arscastitatis.component;

import java.util.function.UnaryOperator;
import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Mod data components. */
public class ModDataComponents {

  public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
      DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, ArsCastitatisMod.MOD_ID);

  public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>>
      COORDINATES = register("coordinates", builder -> builder.persistent(BlockPos.CODEC));

  private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(
      String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
    return DATA_COMPONENTS.register(
        name, regName -> builderOperator.apply(DataComponentType.builder()).build());
  }

  /**
   * Adding registry into a {@link IEventBus}.
   *
   * @param bus mod event bus
   */
  public static void register(IEventBus bus) {
    DATA_COMPONENTS.register(bus);
  }
}
