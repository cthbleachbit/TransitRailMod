package tk.cth451.transitrailmod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.cth451.transitrailmod.References;
import tk.cth451.transitrailmod.blocks.ClosedPlatformDoorBlock;
import tk.cth451.transitrailmod.blocks.ClosedPlatformPanelBlock;
import tk.cth451.transitrailmod.blocks.ClosedPlatformTop;
import tk.cth451.transitrailmod.blocks.HungArrowSign;
import tk.cth451.transitrailmod.blocks.LogoBlock;
import tk.cth451.transitrailmod.blocks.PlatformGateBlock;
import tk.cth451.transitrailmod.blocks.PlatformPanelBlock;

public class ModBlocks {
	public static Block closed_platform_top;
	public static Block closed_platform_door_block;
	public static Block closed_platform_panel_block;
	public static Block platform_gate_block;
	public static Block platform_panel_block;
	public static Block logo_block;
	public static Block hung_arrow_sign;
	public static Block platform_sign;
	
	public static void init() {
		closed_platform_top = new ClosedPlatformTop(Material.cloth);
		closed_platform_door_block = new ClosedPlatformDoorBlock(Material.glass);
		closed_platform_panel_block = new ClosedPlatformPanelBlock(Material.glass);
		platform_gate_block = new PlatformGateBlock(Material.glass);
		platform_panel_block = new PlatformPanelBlock(Material.iron);
		logo_block = new LogoBlock(Material.iron);
		hung_arrow_sign = new HungArrowSign(Material.iron);
		//platform_sign = new PlatformSign(Material.iron);
	}
	
	public static void register() {
		GameRegistry.registerBlock(closed_platform_top, closed_platform_top.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(closed_platform_door_block, closed_platform_door_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(closed_platform_panel_block, closed_platform_panel_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(platform_gate_block, platform_gate_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(platform_panel_block, platform_panel_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(logo_block, logo_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(hung_arrow_sign, hung_arrow_sign.getUnlocalizedName().substring(5));
		//GameRegistry.registerBlock(platform_sign, platform_sign.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders(){
		registerRender(closed_platform_top);
		registerRender(closed_platform_door_block);
		registerRender(closed_platform_panel_block);
		registerRender(platform_gate_block);
		registerRender(platform_panel_block);
		registerRender(logo_block);
		registerRender(hung_arrow_sign);
		registerInventoryRender(hung_arrow_sign);
		//registerRender(platform_sign);
	}
	
	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(References.MOD_ID + ":" + item.getUnlocalizedName().substring(5)));
	}
	
	public static void registerInventoryRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(References.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
