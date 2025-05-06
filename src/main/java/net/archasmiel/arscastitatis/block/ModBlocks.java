package net.archasmiel.arscastitatis.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import net.archasmiel.arscastitatis.ArsCastitatisMod;
import net.archasmiel.arscastitatis.block.custom.FuelBlockItem;
import net.archasmiel.arscastitatis.block.custom.LedLampBlock;
import net.archasmiel.arscastitatis.block.custom.RgbLampBlock;
import net.archasmiel.arscastitatis.block.custom.TeleportBlock;
import net.archasmiel.arscastitatis.block.custom.TransformationBlock;
import net.archasmiel.arscastitatis.block.custom.WiringBayBlock;
import net.archasmiel.arscastitatis.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Class with mod blocks. */
public class ModBlocks {

  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(Registries.BLOCK, ArsCastitatisMod.MOD_ID);

  public static final List<Block> PICKAXE_MINEABLE = new ArrayList<>();
  public static final List<Block> AXE_MINEABLE = new ArrayList<>();
  public static final List<Block> SHOVEL_MINEABLE = new ArrayList<>();
  public static final List<Block> HOE_MINEABLE = new ArrayList<>();

  public static final List<Block> DIAMOND_PICKAXE = new ArrayList<>();
  public static final List<Block> IRON_PICKAXE = new ArrayList<>();
  public static final List<Block> STONE_PICKAXE = new ArrayList<>();

  public static final DeferredHolder<Block, TeleportBlock> REINFORCED_DIORITE;
  public static final DeferredHolder<Block, Block> PAPER_BLOCK;
  public static final DeferredHolder<Block, TransformationBlock> MAGIC_BRICKS;
  public static final DeferredHolder<Block, Block> PLASTIC_BLOCK;
  public static final DeferredHolder<Block, StairBlock> PLASTIC_STAIRS;
  public static final DeferredHolder<Block, SlabBlock> PLASTIC_SLAB;
  public static final DeferredHolder<Block, PressurePlateBlock> PLASTIC_PRESSURE_PLATE;
  public static final DeferredHolder<Block, ButtonBlock> PLASTIC_BUTTON;
  public static final DeferredHolder<Block, FenceBlock> PLASTIC_FENCE;
  public static final DeferredHolder<Block, FenceGateBlock> PLASTIC_FENCE_GATE;
  public static final DeferredHolder<Block, WallBlock> PLASTIC_WALL;
  public static final DeferredHolder<Block, DoorBlock> PLASTIC_DOOR;
  public static final DeferredHolder<Block, TrapDoorBlock> PLASTIC_TRAPDOOR;
  public static final DeferredHolder<Block, LedLampBlock> LED_LAMP;
  public static final DeferredHolder<Block, RgbLampBlock> RGB_LAMP;
  public static final DeferredHolder<Block, WiringBayBlock> WIRING_BAY;

  /**
   * Registers a block along with its corresponding block item in the mod registry.
   *
   * @param name the name of the block to register
   * @param blockFunc a {@link Function} that creates the block given its properties
   * @param blockItemFunc a {@link BiFunction} that creates the block item given the block and item
   *     properties
   * @param blockProps the {@link BlockBehaviour.Properties} for the block (e.g., strength,
   *     occlusion, collision, etc.)
   * @param itemProps the {@link Item.Properties} for the block item (e.g., stack size, food
   *     properties, etc.)
   * @param <T> the custom block type extending {@link Block}
   * @param <F> the custom block item type extending {@link BlockItem}
   * @return a {@link DeferredHolder} representing the registered block
   */
  public static <T extends Block, F extends BlockItem> DeferredHolder<Block, T> registerBlock(
      String name,
      Function<BlockBehaviour.Properties, T> blockFunc,
      BiFunction<T, Item.Properties, F> blockItemFunc,
      BlockBehaviour.Properties blockProps,
      Item.Properties itemProps) {

    var block = BLOCKS.register(name, key -> blockFunc.apply(blockProps));
    ModItems.registerBlockItem(name, blockItemFunc, block, itemProps);
    return block;
  }

  static {
    REINFORCED_DIORITE =
        registerBlock(
            "reinforced_diorite",
            TeleportBlock::new,
            BlockItem::new,
            Blocks.OBSIDIAN.properties(),
            new Item.Properties());
    PAPER_BLOCK =
        registerBlock(
            "paper_block",
            Block::new,
            (b, p) -> new FuelBlockItem(b, p, 1200),
            Blocks.BAMBOO_PLANKS.properties(),
            new Item.Properties());
    MAGIC_BRICKS =
        registerBlock(
            "magic_bricks",
            p ->
                new TransformationBlock(
                    p,
                    Map.of(
                        Items.EMERALD, Items.DIAMOND,
                        Items.GOLD_INGOT, Items.IRON_INGOT,
                        Items.COPPER_INGOT, Items.BRICK)),
            BlockItem::new,
            Blocks.BRICKS.properties(),
            new Item.Properties());

    PLASTIC_BLOCK =
        registerBlock(
            "plastic_block",
            Block::new,
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.BAMBOO)
                .requiresCorrectToolForDrops(),
            new Item.Properties());
    PLASTIC_STAIRS =
        registerBlock(
            "plastic_stairs",
            props -> new StairBlock(PLASTIC_BLOCK.get().defaultBlockState(), props),
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.BAMBOO)
                .requiresCorrectToolForDrops(),
            new Item.Properties());
    PLASTIC_SLAB =
        registerBlock(
            "plastic_slab",
            SlabBlock::new,
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.BAMBOO)
                .requiresCorrectToolForDrops(),
            new Item.Properties());
    PLASTIC_PRESSURE_PLATE =
        registerBlock(
            "plastic_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.BAMBOO)
                .requiresCorrectToolForDrops(),
            new Item.Properties());
    PLASTIC_BUTTON =
        registerBlock(
            "plastic_button",
            props -> new ButtonBlock(BlockSetType.OAK, 20, props),
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.BAMBOO)
                .requiresCorrectToolForDrops()
                .noCollission(),
            new Item.Properties());
    PLASTIC_FENCE =
        registerBlock(
            "plastic_fence",
            FenceBlock::new,
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.BAMBOO)
                .requiresCorrectToolForDrops(),
            new Item.Properties());
    PLASTIC_FENCE_GATE =
        registerBlock(
            "plastic_fence_gate",
            props -> new FenceGateBlock(WoodType.OAK, props),
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.BAMBOO)
                .requiresCorrectToolForDrops(),
            new Item.Properties());
    PLASTIC_WALL =
        registerBlock(
            "plastic_wall",
            WallBlock::new,
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.BAMBOO)
                .requiresCorrectToolForDrops(),
            new Item.Properties());
    PLASTIC_DOOR =
        registerBlock(
            "plastic_door",
            props -> new DoorBlock(BlockSetType.OAK, props),
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.BAMBOO)
                .requiresCorrectToolForDrops()
                .noOcclusion(),
            new Item.Properties());
    PLASTIC_TRAPDOOR =
        registerBlock(
            "plastic_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.OAK, props),
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.BAMBOO)
                .requiresCorrectToolForDrops()
                .noOcclusion(),
            new Item.Properties());

    LED_LAMP =
        registerBlock(
            "led_lamp",
            props -> new LedLampBlock(props, 15),
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.GLASS)
                .requiresCorrectToolForDrops(),
            new Item.Properties());
    RGB_LAMP =
        registerBlock(
            "rgb_lamp",
            RgbLampBlock::new,
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.GLASS)
                .requiresCorrectToolForDrops(),
            new Item.Properties());

    WIRING_BAY =
        registerBlock(
            "wiring_bay",
            WiringBayBlock::new,
            BlockItem::new,
            BlockBehaviour.Properties.of()
                .strength(2f)
                .sound(SoundType.METAL)
                .requiresCorrectToolForDrops(),
            new Item.Properties().stacksTo(1));
  }

  /**
   * Populates the custom creative mode tab with mod blocks.
   *
   * @param parameters the display parameters provided by {@link CreativeModeTab}
   * @param output the output used to accept blocks into the creative tab
   */
  public static void displayCreativeModTab(
      CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {

    output.accept(ModBlocks.REINFORCED_DIORITE.get());
    output.accept(ModBlocks.PAPER_BLOCK.get());
    output.accept(ModBlocks.MAGIC_BRICKS.get());

    output.accept(ModBlocks.PLASTIC_BLOCK.get());
    output.accept(ModBlocks.PLASTIC_STAIRS.get());
    output.accept(ModBlocks.PLASTIC_SLAB.get());
    output.accept(ModBlocks.PLASTIC_PRESSURE_PLATE.get());
    output.accept(ModBlocks.PLASTIC_BUTTON.get());
    output.accept(ModBlocks.PLASTIC_FENCE.get());
    output.accept(ModBlocks.PLASTIC_FENCE_GATE.get());
    output.accept(ModBlocks.PLASTIC_WALL.get());
    output.accept(ModBlocks.PLASTIC_DOOR.get());

    output.accept(ModBlocks.LED_LAMP.get());
    output.accept(ModBlocks.RGB_LAMP.get());

    output.accept(ModBlocks.WIRING_BAY.get());
  }

  /**
   * Adding registry into a {@link IEventBus}.
   *
   * @param bus mod event bus
   */
  public static void register(IEventBus bus) {
    BLOCKS.register(bus);
  }
}
