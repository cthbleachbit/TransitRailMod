package me.cth451.transit.blocks;

import me.cth451.transit.blockentities.TurnstileBlockEntity;
import me.cth451.transit.enums.TowardEnum;
import me.cth451.transit.items.FareCard;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static me.cth451.transit.items.FareCard.KEY_IN_USE;
import static me.cth451.transit.items.FareCard.KEY_BALANCE;

public class Turnstile extends HorizontalFacingBlock implements BlockEntityProvider {
    public static final BooleanProperty OPEN;
    public static final EnumProperty<TowardEnum> TOWARD;

    public Turnstile(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(OPEN, false).with(TOWARD, TowardEnum.IN));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, TOWARD);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        Direction direction = ctx.getPlayerFacing();
        return this.getDefaultState().with(FACING, direction).with(OPEN, false);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new TurnstileBlockEntity();
    }

    private void openGateAndStartCountdown(BlockState state, World world, BlockPos pos) {
        TurnstileBlockEntity entity = (TurnstileBlockEntity) world.getBlockEntity(pos);
        // Open the gate
        world.setBlockState(pos, state.with(OPEN, true));
        // Tell the entity to start count down
        assert entity != null;
        entity.startTick();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack handStack = player.getStackInHand(hand);
        // Check if we have fare card in hand
        if (handStack.getItem().getClass() != FareCard.class) {
            System.err.println("Not a fare card");
            return ActionResult.PASS;
        }

        int balance = handStack.getOrCreateTag().getInt(KEY_BALANCE);
        boolean inUse = handStack.getOrCreateTag().getBoolean(KEY_IN_USE);
        boolean exitGate = state.get(TOWARD) == TowardEnum.OUT;

        System.err.println("balance: " + balance);
        System.err.println("in use: " + inUse);
        // Handle unlimited pass or someone in creative mode
        if (balance == -1 || player.isCreative()) {
            if (world.isClient()) {
                player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            }
            this.openGateAndStartCountdown(state, world, pos);
            System.err.println("Unlimited pass");
            return ActionResult.SUCCESS;
        }

        // Handle entry / exit
        if (balance == 0) {
            return ActionResult.PASS;
        }

        if (inUse && exitGate) {
            System.err.println("Out");
            handStack.getOrCreateTag().putInt(KEY_BALANCE, balance - 1);
            handStack.getOrCreateTag().putBoolean(KEY_IN_USE, false);
            if (world.isClient()) {
                player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1.0F);
            }
            this.openGateAndStartCountdown(state, world, pos);
            return ActionResult.SUCCESS;
        } else if ((!inUse) && (!exitGate)) {
            System.err.println("In");
            handStack.getOrCreateTag().putBoolean(KEY_IN_USE  , true);
            if (world.isClient()) {
                player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 1.0F, 2.0F);
            }
            this.openGateAndStartCountdown(state, world, pos);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    static {
        OPEN = Properties.OPEN;
        TOWARD = EnumProperty.of("towards", TowardEnum.class);
    }


}
