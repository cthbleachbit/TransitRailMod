package tk.cth451.transitrailmod.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.gui.container.TicketMachineContainer;

public class TicketMachineGui extends GuiContainer {
	
	public Container machineContainer;
	private final String blockName;
	
	public TicketMachineGui(InventoryPlayer playerInv, World worldIn, String nameIn, int x, int y , int z) {
		super(new TicketMachineContainer(playerInv, worldIn, x, y, z));
		machineContainer = (TicketMachineContainer) inventorySlots;
		blockName = nameIn;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// TODO Auto-generated method stub

	}

}
