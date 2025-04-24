package net.archasmiel.ars_castitatis.block.custom;

import net.archasmiel.ars_castitatis.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

public class TransformBlock extends Block {

    private final Map<Item, Item> transforms = new HashMap<>();

    public TransformBlock(Properties properties, Map<Item, Item> transforms) {
        super(properties);
        this.transforms.putAll(transforms);
    }

    public void addTransform(Item itemFrom, Item itemTo) {
        if (itemFrom == null || itemTo == null) return;
        if (itemTo.equals(itemFrom)) return;
        transforms.put(itemFrom, itemTo);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            if (isValidItem(itemEntity)) {
                ItemStack stack = itemEntity.getItem();
                Item itemTo = transforms.get(stack.getItem());
                itemEntity.setItem(new ItemStack(itemTo, stack.getCount()));
                level.playSound(entity, pos, SoundEvents.BLAZE_SHOOT, SoundSource.BLOCKS);
            }
        }
    }

    private boolean isValidItem(ItemStack item) {
        return transforms.containsKey(item.getItem())
            && item.is(ModTags.Items.TRANSFORMABLE_ITEMS);
    }

    private boolean isValidItem(ItemEntity item) {
        return isValidItem(item.getItem());
    }

}
