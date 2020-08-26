package me.cth451.transit.blockentities;

import me.cth451.transit.TransitRailMod;
import me.cth451.transit.blocks.Turnstile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

public class TurnstileBlockEntity extends BlockEntity implements Tickable {
    private static class SyncInt {
        public int value;
    }

    // Number of ticks before the turnstile closes its door
    private final SyncInt timer = new SyncInt();

    // Default timeout in seconds
    public static final int DEFAULT_TIMEOUT = 10;
    public static final String KEY_TIMER = "timer";

    public TurnstileBlockEntity() {
        super(TransitRailMod.TURNSTILE_BLOCK_ENTITY);
        synchronized (timer) {
            timer.value = 0;
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        synchronized (timer) {
            tag.putInt(KEY_TIMER, timer.value);
        }
        return tag;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        synchronized (timer) {
            timer.value = tag.getInt(KEY_TIMER);
        }
    }

    @Override
    public void tick() {
        boolean timedOut = false;
        synchronized (timer) {
            if (timer.value > 0) {
                timer.value--;
                if (timer.value == 0) timedOut = true;
            }
        }

        if (!this.world.isClient) {
            if (timedOut) {
                System.err.println("Timed out");
                BlockState newState = this.world.getBlockState(this.getPos()).with(Turnstile.POWERED, false);
                Block turnstile = newState.getBlock();
                this.world.setBlockState(this.getPos(), newState, 3);
                turnstile.neighborUpdate(newState, world, pos, turnstile, pos, false);
                this.markDirty();
            }
        }
    }

    public void startTick() {
        synchronized (timer) {
            this.timer.value = DEFAULT_TIMEOUT * 20;
        }
        this.markDirty();
    }

    public void cancelTick() {
        synchronized (timer) {
            this.timer.value = 0;
        }
        this.markDirty();
    }
}
