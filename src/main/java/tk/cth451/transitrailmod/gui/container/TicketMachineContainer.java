package tk.cth451.transitrailmod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.ModOptions;
import tk.cth451.transitrailmod.gui.inventory.TicketMachineInput;
import tk.cth451.transitrailmod.gui.inventory.TicketMachineOutput;
import tk.cth451.transitrailmod.init.ModItems;
import tk.cth451.transitrailmod.items.TrainTicket;

public class TicketMachineContainer extends Container {
	
	public boolean isResultSlotEmpty;
	
	public TicketMachineInput invInput = new TicketMachineInput(this);
	public TicketMachineOutput invOutput = new TicketMachineOutput();
	public InventoryPlayer invPlayer;
	private final World worldObj;
	public String titleString = "ticketmachine.title";
	public int inSlot1Number;
	public int inSlot2Number;
	public int outSlotNumber;
	public int x = 0;
	public int y = 0;
	public int z = 0;
	
	public TicketMachineContainer(InventoryPlayer playerIn, World worldIn, int xIn, int yIn, int zIn) {
		x = xIn;
		y = yIn;
		z = zIn;
		worldObj = worldIn;
		invPlayer = playerIn;
		
		inSlot1Number = addSlotToContainer(new Slot(invInput, 0, 27, 29)).slotNumber;
		inSlot2Number = addSlotToContainer(new Slot(invInput, 1, 76, 29)).slotNumber;
		
		outSlotNumber = addSlotToContainer(new Slot(invOutput, 0, 134, 29)).slotNumber;
		
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
	public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer playerIn) {
		ItemStack stack = super.slotClick(slotId, clickedButton, mode, playerIn);
		if (!outputSlotEmpty() && slotId == this.outSlotNumber) {
			invInput.clear();
		} else if (slotId == this.inSlot1Number || slotId == this.inSlot2Number) {
			onCraftMatrixChanged(invInput);
		}
		return stack;
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		
		if(!this.worldObj.isRemote) {
			for (int i = 0; i < invInput.getSizeInventory(); i++) {
				ItemStack stack = invInput.getStackInSlot(i);
				if (stack != null) {
					playerIn.dropPlayerItemWithRandomChoice(stack, false);
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
		if (!inputSlot1Empty() && !inputSlot2Empty() && outputSlotEmpty()){
			ItemStack ticket = inv.getStackInSlot(0);
			int num = inv.getStackInSlot(0).stackSize;
			int rides = TrainTicket.getRidesRemaining(ticket) + num * ModOptions.RIDES_PER_ITEM;
			boolean inUse = TrainTicket.isTicketInUse(ticket);
			ItemStack ret = TrainTicket.setRidesRemaining(new ItemStack(ModItems.train_ticket), rides, inUse);
			invOutput.setInventorySlotContents(0, ret);
		} else {
			invOutput.clear();
		}
	}
	
	private boolean inputSlot1Empty(){
		return invInput.getStackInSlot(0) == null;
	}
	private boolean inputSlot2Empty(){
		return invInput.getStackInSlot(1) == null;
	}
	private boolean outputSlotEmpty(){
		return invOutput.getStackInSlot(0) == null;
	}
}
