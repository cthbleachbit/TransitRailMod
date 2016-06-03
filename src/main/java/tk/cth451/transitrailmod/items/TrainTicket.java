package tk.cth451.transitrailmod.items;

import net.minecraft.item.Item;
import tk.cth451.transitrailmod.TransitRailMod;

public class TrainTicket extends Item {
	
	public TrainTicket(){
		super();
		setUnlocalizedName("train_ticket");
		setCreativeTab(TransitRailMod.tabTransitRail);
	}
}
