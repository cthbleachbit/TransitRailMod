package tk.cth451.transitrailmod.init;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import tk.cth451.transitrailmod.EnumGuiTypes;
import tk.cth451.transitrailmod.References;
import tk.cth451.transitrailmod.gui.TicketMachineGui;
import tk.cth451.transitrailmod.gui.container.TicketMachineContainer;

public class ModGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == EnumGuiTypes.TICKET_MACHINE.ordinal())
        {
            return new TicketMachineContainer(player.inventory, world, x, y, z);
        }
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == EnumGuiTypes.TICKET_MACHINE.ordinal())
        {
			return new TicketMachineGui(player.inventory, world, ModBlocks.ticket_machine.getLocalizedName(), x, y, z);
        }
		
		return null;
	}

}
