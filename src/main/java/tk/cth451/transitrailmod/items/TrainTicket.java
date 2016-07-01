package tk.cth451.transitrailmod.items;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.prototype.ArrowSign;

public class TrainTicket extends Item {
	
	public TrainTicket(){
		super();
		setUnlocalizedName("train_ticket");
		setCreativeTab(TransitRailMod.tabTransitRail);
		this.maxStackSize = 1;
		this.setMaxDamage(199);
		// max 200 uses
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		int damage = stack.getItemDamage();
		tooltip.add(damage + "/" + (this.getMaxDamage() + 1));
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		stack.damageItem(1, playerIn);
		return true;
	}
}
