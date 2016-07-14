package tk.cth451.transitrailmod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTicketMachineOutput extends Slot {
	
	private final InventoryTicketMachineInput invIn;
	private final EntityPlayer thePlayer;
	
	public SlotTicketMachineOutput(EntityPlayer playerIn, InventoryTicketMachineInput invTicket, IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		invIn = invTicket;
		thePlayer = playerIn;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
		invIn.clear();
	}
}
