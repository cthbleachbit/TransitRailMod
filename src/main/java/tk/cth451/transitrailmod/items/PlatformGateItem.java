package tk.cth451.transitrailmod.items;

import net.minecraft.item.Item;
import tk.cth451.transitrailmod.TransitRailMod;

public class PlatformGateItem extends Item {
	
	public PlatformGateItem(){
		super();
		setUnlocalizedName("platform_gate_item");
		setCreativeTab(TransitRailMod.tabTransitRail);
	}
}
