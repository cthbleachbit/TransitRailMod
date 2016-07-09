package tk.cth451.transitrailmod;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public abstract class ModOptions {
	public static final int TICKET_MAX_USES = 200;
	public static final int MAX_RIDES = TICKET_MAX_USES / 2;
	// max 100 rides (200 uses)
	
	public static final int RIDES_PER_ITEM = 60;
	// 1 item = n rides
	
	public static final Item ITEM_TICKET_REFILL = Items.emerald;
	// the item used to refill ticket
}
