package tk.cth451.transitrailmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tk.cth451.transitrailmod.init.ModBlocks;
import tk.cth451.transitrailmod.init.ModItems;
import tk.cth451.transitrailmod.init.ModRecipe;
import tk.cth451.transitrailmod.init.ModTileEntities;
import tk.cth451.transitrailmod.proxy.CommonProxy;

@Mod(modid = References.MOD_ID, version = References.VERSION, name = References.VERSION)
public class TransitRailMod
{
	@Instance(References.MOD_ID)
	public static TransitRailMod instance;
	
	@SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final TransitRailTab tabTransitRail = new TransitRailTab("tabTransitRail");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ModBlocks.init();
		ModBlocks.register();
		ModItems.init();
		ModItems.register();
		ModTileEntities.register();
  	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		ModRecipe.addItemsRecipe();
		ModRecipe.addBlocksRecipe();
		proxy.registerRenders();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
