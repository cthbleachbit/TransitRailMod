package me.cth451.transit.items;

import net.minecraft.block.GlassBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.List;

public class FareCard extends Item {
    public static final String KEY_BALANCE = "balance";
    public static final String KEY_IN_USE = "in_use";

    public FareCard(Settings settings) {
        super(settings.group(ItemGroup.TRANSPORTATION).maxCount(1));
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        stack.getOrCreateTag().putBoolean(KEY_IN_USE, false);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        int balance = stack.getOrCreateTag().getInt(KEY_BALANCE);
        stack.getOrCreateTag().putInt(KEY_BALANCE, balance);
        if (balance == -1) {
            tooltip.add(new TranslatableText("item.transit.fare_card.unlimited_tooltip"));
        } else {
            if (balance < 0) {
                stack.getOrCreateTag().putInt(KEY_IN_USE, 0);
                balance = 0;
            }
            tooltip.add(new TranslatableText("item.transit.fare_card.balance_tooltip", balance));
        }
        if (stack.getOrCreateTag().getBoolean(KEY_IN_USE)) {
            tooltip.add(new TranslatableText("item.transit.fare_card.in_use_tooltip"));
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        // TODO: change this when the turnstile block is added to this mod.
        if (context.getWorld().getBlockState(context.getBlockPos()).getBlock().getClass() != GlassBlock.class) {
            return ActionResult.PASS;
        }
        int balance = context.getStack().getOrCreateTag().getInt(KEY_BALANCE);
        boolean in_use = context.getStack().getOrCreateTag().getBoolean(KEY_IN_USE);

        // Handle unlimited pass or someone in creative mode
        if (balance == -1 || context.getPlayer().isCreative()) {
            if (context.getWorld().isClient()) {
                context.getPlayer().playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            }
            return ActionResult.SUCCESS;
        }

        // Handle entry / exit
        if (balance == 0) {
            return ActionResult.PASS;
        }

        if (in_use) {
            context.getStack().getOrCreateTag().putInt(KEY_BALANCE, balance - 1);
            context.getStack().getOrCreateTag().putBoolean(KEY_IN_USE, false); 
            if (context.getWorld().isClient()) {
                context.getPlayer().playSound(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1.0F);
            }
        } else {
            context.getStack().getOrCreateTag().putBoolean(KEY_IN_USE  , true);
            if (context.getWorld().isClient()) {
                context.getPlayer().playSound(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 1.0F, 2.0F);
            }
        }

        return ActionResult.SUCCESS;
    }
}
