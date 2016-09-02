package tk.cth451.transitrailmod.init;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import tk.cth451.transitrailmod.EnumGuiTypes;
import tk.cth451.transitrailmod.client.gui.GuiTicketMachine;
import tk.cth451.transitrailmod.inventory.ContainerTicketMachine;

public class ModGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == EnumGuiTypes.TICKET_MACHINE.ordinal())
        {
            return new ContainerTicketMachine(player.inventory, world, x, y, z);
        }
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == EnumGuiTypes.TICKET_MACHINE.ordinal())
        {
			return new GuiTicketMachine(player.inventory, world, ModBlocks.ticket_machine.getLocalizedName(), x, y, z);
        }
		
		return null;
	}

}
