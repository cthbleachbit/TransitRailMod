package tk.cth451.transitrailmod.items;

import net.minecraft.block.Block;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.PlatformPanelBlock;
import tk.cth451.transitrailmod.init.ModBlocks;
import tk.cth451.transitrailmod.items.prototype.UpperLowerDirectionalItem;

public class PlatformPanelItem extends UpperLowerDirectionalItem {
	
	private static Block panel = ModBlocks.platform_panel_block;
	
	public PlatformPanelItem(){
		super(panel, PlatformPanelBlock.UPPER, PlatformPanelBlock.FACING);
		setUnlocalizedName("platform_panel_item");
		setCreativeTab(TransitRailMod.tabTransitRail);
	}
}
