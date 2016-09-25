package tk.cth451.transitrailmod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.ModOptions;
import tk.cth451.transitrailmod.init.ModItems;
import tk.cth451.transitrailmod.items.TrainTicket;

public class ContainerTicketMachine extends Container {
	
	public InventoryTicketMachineInput invInput = new InventoryTicketMachineInput(this);
	public InventoryTicketMachineOutput invOutput = new InventoryTicketMachineOutput();
	public InventoryPlayer invPlayer;
	private final World worldObj;
	public String titleString = "ticketmachine.title";
	public int inSlot1Number;
	public int inSlot2Number;
	public int outSlotNumber;
	public int x = 0;
	public int y = 0;
	public int z = 0;
	
	public ContainerTicketMachine(InventoryPlayer playerIn, World worldIn, int xIn, int yIn, int zIn) {
		x = xIn;
		y = yIn;
		z = zIn;
		worldObj = worldIn;
		invPlayer = playerIn;
		
		inSlot1Number = addSlotToContainer(new Slot(invInput, 0, 27, 29)).slotNumber;
		inSlot2Number = addSlotToContainer(new Slot(invInput, 1, 76, 29)).slotNumber;
		
		outSlotNumber = addSlotToContainer(new SlotTicketMachineOutput(playerIn.player, invInput, invOutput, 0, 134, 29)).slotNumber;
		
		for(int playerSlotIndexY = 0; playerSlotIndexY < 3; ++playerSlotIndexY)
		{
			for(int playerSlotIndexX = 0; playerSlotIndexX < 9; ++playerSlotIndexX)
			{
				addSlotToContainer(new Slot(playerIn,
						playerSlotIndexX + playerSlotIndexY * 9 + 9,
						8 + playerSlotIndexX * 18, 84 + playerSlotIndexY * 18));
			}
		}
		
		for(int hotbarSlotIndex = 0; hotbarSlotIndex < 9; ++hotbarSlotIndex)
		{
			addSlotToContainer(new Slot(playerIn, hotbarSlotIndex,
					8 + hotbarSlotIndex * 18, 142));
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		
		if(!this.worldObj.isRemote) {
			for (int i = 0; i < invInput.getSizeInventory(); i++) {
				ItemStack stack = invInput.getStackInSlot(i);
				if (stack != null) {
					playerIn.dropItem(stack, false);
				}
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inv){
		invOutput.setInventorySlotContents(0, this.getProcessedTicket(invInput));
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < 3) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else if (index >= 3 && index < 30) {
				if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
					return null;
				}
			} else if (index >= 30 && index < 39) {
				if (!this.mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack)null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(playerIn, itemstack1);
		}
		return itemstack;
	}
	
	@Override
	public boolean canMergeSlot(ItemStack p_94530_1_, Slot p_94530_2_) {
		return p_94530_2_.inventory != this.invOutput && super.canMergeSlot(p_94530_1_, p_94530_2_);
	}
	
	// private methods
	private boolean inputSlot1NotEmpty(){
		ItemStack stack = invInput.getStackInSlot(0);
		return stack != null;
	}
	
	private boolean inputSlot1IsPaper(){
		ItemStack stack = invInput.getStackInSlot(0);
		if (stack == null) {
			return false;
		}
		return stack.getItem() == Items.PAPER;
	}
	
	private boolean inputSlot1IsTicket(){
		ItemStack stack = invInput.getStackInSlot(0);
		if (stack == null) {
			return false;
		}
		return stack.getItem() == ModItems.train_ticket;
	}
	
	private boolean inputSlot2Valid(){
		ItemStack stack = invInput.getStackInSlot(1);
		if (stack == null) {
			return false;
		}
		return stack.getItem() == Items.EMERALD;
	}
	
	protected ItemStack getProcessedTicket(InventoryTicketMachineInput input) {
		if (inputSlot1NotEmpty() && inputSlot2Valid()) {
			int rides;
			boolean inUse;
			if (inputSlot1IsTicket()) {
				ItemStack ticket = input.getStackInSlot(0);
				rides = TrainTicket.getRidesRemaining(ticket) + ModOptions.RIDES_PER_ITEM;
				inUse = TrainTicket.isTicketInUse(ticket);
			} else {
				rides = ModOptions.RIDES_PER_ITEM - ModOptions.TICKET_CONVERSION_FEE;
				inUse = false;
			}
			return TrainTicket.setRidesRemaining(new ItemStack(ModItems.train_ticket), rides, inUse);
		} else {
			return null;
		}
	}
}
