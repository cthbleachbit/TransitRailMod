package tk.cth451.transitrailmod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import tk.cth451.transitrailmod.gui.inventory.TicketMachineInput;
import tk.cth451.transitrailmod.gui.inventory.TicketMachineOutput;

public class TicketMachineContainer extends Container {
	
	public boolean isResultSlotEmpty;
	public static final int VALUE_PER_EMERALD = 100;
	
	public TicketMachineInput invInput = new TicketMachineInput(this);
	public TicketMachineOutput invOut = new TicketMachineOutput();
	
	public TicketMachineContainer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void onChanged(IInventory inv){
		Item ticket = inv.getStackInSlot(0).getItem();
		Item emerald = inv.getStackInSlot(1).getItem();
	}
}
