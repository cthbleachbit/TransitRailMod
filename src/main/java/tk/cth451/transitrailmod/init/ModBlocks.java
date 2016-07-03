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
import tk.cth451.transitrailmod.blocks.FluorescentLamp;
import tk.cth451.transitrailmod.blocks.SlimPassengerDetector;
import tk.cth451.transitrailmod.blocks.HungArrowSign;
import tk.cth451.transitrailmod.blocks.LogoBlock;
import tk.cth451.transitrailmod.blocks.PlatformArrowSign;
import tk.cth451.transitrailmod.blocks.PlatformGateBlock;
import tk.cth451.transitrailmod.blocks.PlatformPanelBlock;
import tk.cth451.transitrailmod.blocks.TurnstileBlock;
import tk.cth451.transitrailmod.blocks.WirePanel;
import tk.cth451.transitrailmod.blocks.WirePanelCorner;

public class ModBlocks {
	public static Block closed_platform_top;
	public static Block closed_platform_door_block;
	public static Block closed_platform_panel_block;
	public static Block platform_gate_block;
	public static Block platform_panel_block;
	public static Block logo_block;
	public static Block hung_arrow_sign;
	public static Block platform_arrow_sign;
	public static Block fluorescent_lamp;
	public static Block wire_panel;
	public static Block wire_panel_corner;
	public static Block platform_sign;
	public static Block turnstile_block;
	public static Block slim_passenger_detector;
	
	public static void init() {
		closed_platform_top = new ClosedPlatformTop(Material.cloth);
		closed_platform_door_block = new ClosedPlatformDoorBlock(Material.glass);
		closed_platform_panel_block = new ClosedPlatformPanelBlock(Material.glass);
		platform_gate_block = new PlatformGateBlock(Material.glass);
		platform_panel_block = new PlatformPanelBlock(Material.iron);
		logo_block = new LogoBlock(Material.iron);
		hung_arrow_sign = new HungArrowSign(Material.iron);
		platform_arrow_sign = new PlatformArrowSign(Material.iron);
		fluorescent_lamp = new FluorescentLamp(Material.glass);
		wire_panel = new WirePanel(Material.iron);
		wire_panel_corner = new WirePanelCorner(Material.iron);
		//platform_sign = new PlatformSign(Material.iron);
		turnstile_block = new TurnstileBlock(Material.iron);
		slim_passenger_detector = new SlimPassengerDetector(Material.iron);
	}
	
	public static void register() {
		GameRegistry.registerBlock(closed_platform_top, closed_platform_top.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(closed_platform_door_block, closed_platform_door_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(closed_platform_panel_block, closed_platform_panel_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(platform_gate_block, platform_gate_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(platform_panel_block, platform_panel_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(logo_block, logo_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(hung_arrow_sign, hung_arrow_sign.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(platform_arrow_sign, platform_arrow_sign.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(fluorescent_lamp, fluorescent_lamp.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(wire_panel, wire_panel.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(wire_panel_corner, wire_panel_corner.getUnlocalizedName().substring(5));
		//GameRegistry.registerBlock(platform_sign, platform_sign.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(turnstile_block, turnstile_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(slim_passenger_detector, slim_passenger_detector.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders(){
		registerRender(closed_platform_top);
		registerRender(closed_platform_door_block);
		registerRender(closed_platform_panel_block);
		registerRender(platform_gate_block);
		registerRender(platform_panel_block);
		registerRender(logo_block);
		registerRender(hung_arrow_sign);
		registerRender(platform_arrow_sign);
		registerRender(fluorescent_lamp);
		registerRender(wire_panel);
		registerRender(wire_panel_corner);
		//registerRender(platform_sign);
		registerRender(turnstile_block);
		registerRender(slim_passenger_detector);
	}
	
	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(References.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
