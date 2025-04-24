package net.archasmiel.ars_castitatis.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class TeleportBlock extends Block {

    private static final Random RANDOM = new Random();

    public TeleportBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {

        if (entity instanceof ServerPlayer player) {
            BlockPos newPos = pos.offset(RANDOM.nextInt(-5, 6), RANDOM.nextInt(1, 6), RANDOM.nextInt(-5, 6));
            player.teleportTo(newPos.getX(), newPos.getY(), newPos.getZ());

            level.playSound(null, newPos, SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
            player.resetFallDistance();
            player.resetCurrentImpulseContext();
        }

        for(int i = 0; i < 32; ++i) {
            level.addParticle(ParticleTypes.PORTAL,
                pos.getX(), pos.getY() + RANDOM.nextDouble() * (double)2.0F, pos.getZ(),
                RANDOM.nextGaussian(), 0.0F, RANDOM.nextGaussian());
        }
    }
}
