package me.cth451.transit.items;

import me.cth451.transit.TransitRailMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.GlassBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.datafixer.fix.RecipeFix;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.*;
import net.minecraft.recipe.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.List;

public class FareTicketItem extends Item {
    public FareTicketItem(Settings settings) {
        super(settings.group(ItemGroup.TRANSPORTATION).maxCount(1));
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        stack.getOrCreateTag().putInt("balance", 0);
        stack.getOrCreateTag().putBoolean("in_use", false);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        int balance = stack.getOrCreateTag().getInt("balance");
        if (balance == -1) {
            tooltip.add(new TranslatableText("item.transit.fare_ticket.unlimited_tooltip"));
        } else {
            if (balance < 0) {
                stack.getOrCreateTag().putInt("balance", 0);
                balance = 0;
            }
            tooltip.add(new TranslatableText("item.transit.fare_ticket.balance_tooltip", balance));
        }
        if (stack.getOrCreateTag().getBoolean("in_use")) {
            tooltip.add(new TranslatableText("item.transit.fare_ticket.in_use_tooltip"));
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
            context.getPlayer().playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            return ActionResult.SUCCESS;
        }

        // Handle entry / exit
        if (balance == 0) {
            return ActionResult.FAIL;
        }

        if (in_use) {
            context.getStack().getOrCreateTag().putInt("balance", balance - 1);
            context.getStack().getOrCreateTag().putBoolean("in_use", false);
        } else {
            context.getStack().getOrCreateTag().putBoolean("in_use", true);
        }

        context.getPlayer().playSound(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1.0F);
        return ActionResult.SUCCESS;
    }

    public class TicketCombineRecipe implements CraftingRecipe {

        @Override
        public boolean matches(CraftingInventory inv, World world) {
            ArrayList<ItemStack> items = new ArrayList<>();
            for (int i = 0; i < inv.getHeight(); i++) {
                for (int j = 0; j < inv.getWidth(); j++) {
                    int slot = i * inv.getWidth() + j;
                    ItemStack stack = inv.getStack(slot);
                    if (stack.getItem().equals(TransitRailMod.FARE_TICKET)) {
                        items.add(inv.getStack(slot));
                    } else if (!stack.isEmpty()) {
                        return false;
                    }
                }
            }
            System.out.println("Success matching");
            return true;
        }

        @Override
        public ItemStack craft(CraftingInventory inv) {
            return null;
        }

        @Override
        public boolean fits(int width, int height) {
            return false;
        }

        @Override
        public ItemStack getOutput() {
            return null;
        }

        @Override
        public DefaultedList<ItemStack> getRemainingStacks(CraftingInventory inventory) {
            return null;
        }

        @Override
        public DefaultedList<Ingredient> getPreviewInputs() {
            return null;
        }

        @Override
        public boolean isIgnoredInRecipeBook() {
            return true;
        }

        @Override
        public String getGroup() {
            return null;
        }

        @Override
        public ItemStack getRecipeKindIcon() {
            return null;
        }

        @Override
        public Identifier getId() {
            return null;
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return null;
        }

        @Override
        public RecipeType<?> getType() {
            return null;
        }
    }
}
