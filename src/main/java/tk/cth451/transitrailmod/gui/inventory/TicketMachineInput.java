package tk.cth451.transitrailmod.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import tk.cth451.transitrailmod.gui.container.TicketMachineContainer;
import tk.cth451.transitrailmod.init.ModItems;

// [] []
// 1  2  3
// 1 = Ticket in
// 2 = Emerald in
public class TicketMachineInput implements IInventory {
	
	private final ItemStack[] invStack = new ItemStack[2];
	
    private final TicketMachineContainer eventHandler;
    
    public TicketMachineInput(TicketMachineContainer cont) {
		this.eventHandler = cont;
	}
    
	@Override
	public String getName() {
		return "TicketMachineInput";
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IChatComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return invStack[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (invStack[index] != null) {
			ItemStack ret = invStack[index];
			invStack[index] = null;
			this.eventHandler.onChanged(this);
			return ret;
		} else {
			this.eventHandler.onChanged(this);
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if (invStack[index] != null) {
			ItemStack ret = invStack[index];
			invStack[index] = null;
			this.eventHandler.onChanged(this);
			return ret;
		} else {
			this.eventHandler.onChanged(this);
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		invStack[index] = stack;
		this.eventHandler.onChanged(this);
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		switch (index) {
		case 2:
			return stack.getItem() == Items.emerald;
		default: // 1
			return stack.getItem() == ModItems.train_ticket;
		}
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < invStack.length; i++) {
			invStack[i] = null;
		}
	}

}
