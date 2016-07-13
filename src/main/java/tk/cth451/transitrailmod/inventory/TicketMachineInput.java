package tk.cth451.transitrailmod.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

public class TicketMachineInput extends InventoryCrafting {

	public TicketMachineInput(Container p_i1807_1_) {
		super(p_i1807_1_, 2, 1);
	}

	@Override
	public String getName() {
		return "transitrailmod.ticket_machine";
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}
}
