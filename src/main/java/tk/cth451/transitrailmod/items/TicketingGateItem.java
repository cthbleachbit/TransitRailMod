package tk.cth451.transitrailmod.items;

import net.minecraft.item.Item;
import tk.cth451.transitrailmod.TransitRailMod;

public class TicketingGateItem extends Item {
	
	public TicketingGateItem(){
		super();
		setUnlocalizedName("ticketing_gate_item");
		setCreativeTab(TransitRailMod.tabTransitRail);
	}
}
