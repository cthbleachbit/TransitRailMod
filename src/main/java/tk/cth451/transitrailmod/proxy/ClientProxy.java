package tk.cth451.transitrailmod.proxy;

import tk.cth451.transitrailmod.init.ModBlocks;
import tk.cth451.transitrailmod.init.ModItems;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerRenders(){
		ModBlocks.registerRenders();
		ModItems.registerRenders();
	}
}
