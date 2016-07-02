package tk.cth451.transitrailmod.items;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.prototype.ArrowSign;

public class TrainTicket extends Item {
	
	public TrainTicket(){
		super();
		setUnlocalizedName("train_ticket");
		setCreativeTab(TransitRailMod.tabTransitRail);
		this.maxStackSize = 1;
		this.setMaxDamage(200);
		// max 100 rides (200 uses)
	}
	
	// transitrailmod.ticket.in_use
	// transitrailmod.ticket.not_in_use
	// transitrailmod.ticket.remaining_rides
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		int damage = stack.getItemDamage();
		String usageLangKey = isTicketInUse(damage) ? "transitrailmod.ticket.in_use" : "transitrailmod.ticket.not_in_use";
		String usageToolTip = StatCollector.translateToLocal(usageLangKey);
		tooltip.add(usageToolTip);
		String ridesToolTip = StatCollector.translateToLocal("transitrailmod.ticket.remaining_rides");
		tooltip.add(ridesToolTip + ": " + getRidesRemaining(damage, this.getMaxDamage()));
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		stack.damageItem(1, playerIn);
		return true;
	}
	
	// damage to rides conversion
	// n rides = 2 n uses = 2 n - 1 max damage
	// On entry, the damage should be added 1 if and only if the damage is an even number.
	// On exit, the damage should be added 1 if and only if the damage is an odd number.
	public static boolean isTicketInUse(int damage) {
		return damage % 2 == 1;
	}
	
	public static int getRidesRemaining(int damage, int maxUses) {
		return (maxUses - damage) / 2; 
	}
}
