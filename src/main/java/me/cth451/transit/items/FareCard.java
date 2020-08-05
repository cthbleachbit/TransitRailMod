package me.cth451.transit.items;

import net.minecraft.block.GlassBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class FareCard extends Item {
    public FareCard(Settings settings) {
        super(settings.group(ItemGroup.TRANSPORTATION).maxCount(1));
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        stack.getOrCreateTag().putBoolean("in_use", false);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        int balance = stack.getOrCreateTag().getInt("balance");
        if (balance == -1) {
            tooltip.add(new TranslatableText("item.transit.fare_card.unlimited_tooltip"));
        } else {
            if (balance < 0) {
                stack.getOrCreateTag().putInt("balance", 0);
                balance = 0;
            }
            tooltip.add(new TranslatableText("item.transit.fare_card.balance_tooltip", balance));
        }
        if (stack.getOrCreateTag().getBoolean("in_use")) {
            tooltip.add(new TranslatableText("item.transit.fare_card.in_use_tooltip"));
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().getBlockState(context.getBlockPos()).getBlock().getClass() != GlassBlock.class) {
            return ActionResult.PASS;
        }
        int balance = context.getStack().getOrCreateTag().getInt("balance");
        boolean in_use = context.getStack().getOrCreateTag().getBoolean("in_use");

        // Handle unlimited pass or someone in creative mode
        if (balance == -1 || context.getPlayer().isCreative()) {
            if (context.getWorld().isClient()) {
                context.getPlayer().playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            }
            return ActionResult.SUCCESS;
        }

        // Handle entry / exit
        if (balance == 0) {
            return ActionResult.FAIL;
        }

        if (in_use) {
            context.getStack().getOrCreateTag().putInt("balance", balance - 1);
            context.getStack().getOrCreateTag().putBoolean("in_use", false);
            if (context.getWorld().isClient()) {
                context.getPlayer().playSound(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1.0F);
            }
        } else {
            context.getStack().getOrCreateTag().putBoolean("in_use", true);
            if (context.getWorld().isClient()) {
                context.getPlayer().playSound(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 1.0F, 2.0F);
            }
        }

        return ActionResult.SUCCESS;
    }
}
