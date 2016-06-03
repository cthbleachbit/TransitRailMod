package tk.cth451.transitrailmod.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import tk.cth451.transitrailmod.PlatformSignMessage;

public class PlatformSignEntity extends TileEntity{
	private PlatformSignMessage message;
	
	public PlatformSignEntity(String messageIn){
		message = new PlatformSignMessage(messageIn);
	}
	
	public void setMessage(String messageIn) {
		if (message == null) {
			message = new PlatformSignMessage(messageIn);
		} else {
			message.setMessage(messageIn);
		}
	}
	
	public String getMessage(){
		return message.getMessage();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		message = new PlatformSignMessage((compound.getString("message")));
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		super.writeToNBT(compound);	
		compound.setString("message", message.getMessage());
	}
}
