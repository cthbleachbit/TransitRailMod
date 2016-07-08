package tk.cth451.transitrailmod.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import tk.cth451.transitrailmod.init.ModItems;

// []
// 1
// 1 = Ticket out
public class TicketMachineOutput implements IInventory {
	
	private final ItemStack[] invStack = new ItemStack[1];

	@Override
	public String getName() {
		return "TicketMachineOutput";
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
		return 1;
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
			return ret;
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if (invStack[index] != null) {
			ItemStack ret = invStack[index];
			invStack[index] = null;
			return ret;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		invStack[index] = stack;
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
		return false;
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
