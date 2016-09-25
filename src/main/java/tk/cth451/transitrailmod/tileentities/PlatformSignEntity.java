package tk.cth451.transitrailmod.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class PlatformSignEntity extends TileEntity{
	private String dataContent;
	
	public PlatformSignEntity(String stringIn) {
		dataContent = stringIn;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setString("content", dataContent);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.dataContent = compound.getString("content");
	}
}
