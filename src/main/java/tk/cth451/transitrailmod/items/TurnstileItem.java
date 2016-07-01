package tk.cth451.transitrailmod.items;

import net.minecraft.item.Item;
import tk.cth451.transitrailmod.TransitRailMod;

public class TurnstileItem extends Item {
	
	public TurnstileItem(){
		super();
		setUnlocalizedName("turnstile_item");
		setCreativeTab(TransitRailMod.tabTransitRail);
	}
}
