package tk.cth451.transitrailmod.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class PlatformSignEntity extends TileEntity{
	private String dataContent;
	
	public PlatformSignEntity(String stringIn) {
		dataContent = stringIn;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setString("content", dataContent);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.dataContent = compound.getString("content");
	}
}
