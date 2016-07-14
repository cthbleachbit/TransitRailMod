package tk.cth451.transitrailmod.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.inventory.ContainerTicketMachine;

public class GuiTicketMachine extends GuiContainer {
	
	public Container machineContainer;
	private final String blockName;
	
	public GuiTicketMachine(InventoryPlayer playerInv, World worldIn, String nameIn, int x, int y , int z) {
		super(new ContainerTicketMachine(playerInv, worldIn, x, y, z));
		machineContainer = (ContainerTicketMachine) inventorySlots;
		blockName = nameIn;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(I18n.format("container.ticket_machine.title", new Object[0]), 6, 6, 4210752);
		
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
