package tk.cth451.transitrailmod.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.TurnstileBlock;
import tk.cth451.transitrailmod.blocks.prototype.ArrowSign;
import tk.cth451.transitrailmod.init.ModBlocks;

public class StyleChanger extends Item {
	
	public StyleChanger(){
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(63);
		// max 64 uses
		this.setUnlocalizedName("style_changer");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		IBlockState state = worldIn.getBlockState(pos);
		
		if (state.getBlock() instanceof ArrowSign)
		{
			worldIn.setBlockState(pos, state.cycleProperty(ArrowSign.ARROW));
			stack.damageItem(1, playerIn);
			return true;
		} else if (state.getBlock() == ModBlocks.turnstile_block) {
			worldIn.setBlockState(pos, state.cycleProperty(TurnstileBlock.PASSING));
			stack.damageItem(1, playerIn);
			return true;
		} else {
			return false;
		}
	}
}
