package tk.cth451.transitrailmod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.TransitRailMod;

public class ClosedPlatformPanelItem extends Item {
	
	public ClosedPlatformPanelItem(){
		super();
		setUnlocalizedName("closed_platform_panel_item");
		setCreativeTab(TransitRailMod.tabTransitRail);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
	}
}
