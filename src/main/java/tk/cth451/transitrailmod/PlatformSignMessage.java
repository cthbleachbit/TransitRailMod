package tk.cth451.transitrailmod;

import net.minecraft.nbt.NBTTagCompound;

public class PlatformSignMessage {
	private String message;

	public PlatformSignMessage(String message) {
		this.message = message.substring(0, 2);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message.substring(0, 2);
	}
}
