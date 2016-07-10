package tk.cth451.transitrailmod.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
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
		GL11.glPushMatrix();
		mc.renderEngine.bindTexture(
				new ResourceLocation("transitrailmod:textures/gui/container/ticket_machine.png"));
		int k = width / 2 - xSize / 2;
		int l = height / 2 - ySize / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		GL11.glPopMatrix();
	}

}
