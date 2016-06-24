package tk.cth451.transitrailmod.items;

import net.minecraft.block.Block;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.PlatformGateBlock;
import tk.cth451.transitrailmod.init.ModBlocks;
import tk.cth451.transitrailmod.items.prototype.UpperLowerDirectionalItem;

public class PlatformGateItem extends UpperLowerDirectionalItem {
	
	private static Block gate = ModBlocks.platform_gate_block;
	
	public PlatformGateItem(){
		super(gate, PlatformGateBlock.UPPER, PlatformGateBlock.FACING);
		setUnlocalizedName("platform_gate_item");
		setCreativeTab(TransitRailMod.tabTransitRail);
	}
}
