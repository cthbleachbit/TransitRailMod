package tk.cth451.transitrailmod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.cth451.transitrailmod.References;
import tk.cth451.transitrailmod.blocks.ClosedPlatformPanelBlock;
import tk.cth451.transitrailmod.blocks.ClosedPlatformTop;
import tk.cth451.transitrailmod.blocks.LogoBlock;

public class ModBlocks {
	public static Block closed_platform_top;
	public static Block closed_platform_door_block;
	public static Block closed_platform_panel_block;
	public static Block logo_block;
	public static Block platform_sign;
	
	public static void init() {
		closed_platform_top = new ClosedPlatformTop(Material.cloth);
		closed_platform_panel_block = new ClosedPlatformPanelBlock(Material.glass);
		logo_block = new LogoBlock(Material.iron);
		//platform_sign = new PlatformSign(Material.iron);
	}
	
	public static void register() {
		GameRegistry.registerBlock(closed_platform_top, closed_platform_top.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(closed_platform_panel_block, closed_platform_panel_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(logo_block, logo_block.getUnlocalizedName().substring(5));
		//GameRegistry.registerBlock(platform_sign, platform_sign.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders(){
		registerRender(closed_platform_top);
		registerRender(closed_platform_panel_block);
		registerRender(logo_block);
		//registerRender(platform_sign);
	}
	
	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(References.MOD_ID + ":" + item.getUnlocalizedName().substring(5)));
	}
}
