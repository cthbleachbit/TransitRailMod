package tk.cth451.transitrailmod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import tk.cth451.transitrailmod.ModOptions;
import tk.cth451.transitrailmod.gui.inventory.TicketMachineInput;
import tk.cth451.transitrailmod.gui.inventory.TicketMachineOutput;
import tk.cth451.transitrailmod.init.ModItems;
import tk.cth451.transitrailmod.items.TrainTicket;

public class TicketMachineContainer extends Container {
	
	public boolean isResultSlotEmpty;
	
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
		ItemStack ticket = inv.getStackInSlot(0);
		int num = inv.getStackInSlot(1).stackSize;
		int rides = TrainTicket.getRidesRemaining(ticket) + num * ModOptions.RIDES_PER_ITEM;
		boolean inUse = TrainTicket.isTicketInUse(ticket);
		ItemStack ret = TrainTicket.setRidesRemaining(new ItemStack(ModItems.train_ticket), rides, inUse);
		invOut.setInventorySlotContents(0, ret);
	}
}
