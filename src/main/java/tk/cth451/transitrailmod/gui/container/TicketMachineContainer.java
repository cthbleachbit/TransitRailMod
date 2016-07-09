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
		
		addSlotToContainer(new Slot(invInput, 0, 112 + 18, 7 + 0 * 18));
		addSlotToContainer(new Slot(invInput, 1, 112 + 18, 7 + 1 * 18));
		
		outSlotNumber = addSlotToContainer(new Slot(invOutput, 0, 30 + 15, 35)).slotNumber;
		
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
		onChanged(invInput);
		return stack;
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		if(invPlayer.getItemStack() != null)
		{
			playerIn.entityDropItem(invPlayer.getItemStack(), 0.5f);
		}
		if(!worldObj.isRemote)
		{
			ItemStack itemStack = invOutput.getStackInSlotOnClosing(0);
			if(itemStack != null)
			{
				playerIn.entityDropItem(itemStack, 0.5f);
			}

			for(int i = 0; i < invInput.getSizeInventory(); i++ )
			{
				itemStack = invInput.getStackInSlotOnClosing(i);

				if(itemStack != null)
				{
					playerIn.entityDropItem(itemStack, 0.5f);
				}
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void onChanged(IInventory inv){
		ItemStack ticket = inv.getStackInSlot(0);
		int num = inv.getStackInSlot(0).stackSize;
		int rides = TrainTicket.getRidesRemaining(ticket) + num * ModOptions.RIDES_PER_ITEM;
		boolean inUse = TrainTicket.isTicketInUse(ticket);
		ItemStack ret = TrainTicket.setRidesRemaining(new ItemStack(ModItems.train_ticket), rides, inUse);
		invOutput.setInventorySlotContents(0, ret);
	}
}
