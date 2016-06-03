package tk.cth451.transitrailmod.items;

import net.minecraft.item.Item;
import tk.cth451.transitrailmod.TransitRailMod;

public class TicketMachineItem extends Item {
	
	public TicketMachineItem(){
		super();
		setUnlocalizedName("ticket_machine_item");
		setCreativeTab(TransitRailMod.tabTransitRail);
	}
}
