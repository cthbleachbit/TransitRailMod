package tk.cth451.transitrailmod.items;

import net.minecraft.item.Item;
import tk.cth451.transitrailmod.TransitRailMod;

public class ClosedPlatformDoorItem extends Item {
	
	public ClosedPlatformDoorItem(){
		super();
		setUnlocalizedName("closed_platform_door_item");
		setCreativeTab(TransitRailMod.tabTransitRail);
	}
}
