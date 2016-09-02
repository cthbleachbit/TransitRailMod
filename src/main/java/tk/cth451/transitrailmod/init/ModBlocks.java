package tk.cth451.transitrailmod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.cth451.transitrailmod.References;
import tk.cth451.transitrailmod.blocks.ClosedPlatformDoorBlock;
import tk.cth451.transitrailmod.blocks.ClosedPlatformPanelBlock;
import tk.cth451.transitrailmod.blocks.ClosedPlatformTop;
import tk.cth451.transitrailmod.blocks.FluorescentLamp;
import tk.cth451.transitrailmod.blocks.GlassFenceBlock;
import tk.cth451.transitrailmod.blocks.HungArrowSign;
import tk.cth451.transitrailmod.blocks.LogoBlock;
import tk.cth451.transitrailmod.blocks.NoiseBarrierBlock;
import tk.cth451.transitrailmod.blocks.PlatformArrowSign;
import tk.cth451.transitrailmod.blocks.PlatformGateBlock;
import tk.cth451.transitrailmod.blocks.PlatformPanelBlock;
import tk.cth451.transitrailmod.blocks.SlimPassengerDetector;
import tk.cth451.transitrailmod.blocks.TicketMachine;
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
	public static Block ticket_machine;
	public static Block glass_fence;
	public static Block noise_barrier;
	public static Block noise_barrier_with_lamp;
	public static Block slim_passenger_detector;
	
	public static void init() {
		closed_platform_top = new ClosedPlatformTop(Material.IRON);
		closed_platform_door_block = new ClosedPlatformDoorBlock(Material.GLASS);
		closed_platform_panel_block = new ClosedPlatformPanelBlock(Material.GLASS);
		platform_gate_block = new PlatformGateBlock(Material.GLASS);
		platform_panel_block = new PlatformPanelBlock(Material.IRON);
		logo_block = new LogoBlock(Material.IRON);
		hung_arrow_sign = new HungArrowSign(Material.IRON);
		platform_arrow_sign = new PlatformArrowSign(Material.IRON);
		fluorescent_lamp = new FluorescentLamp(Material.GLASS);
		wire_panel = new WirePanel(Material.IRON);
		wire_panel_corner = new WirePanelCorner(Material.IRON);
		//platform_sign = new PlatformSign(Material.IRON);
		turnstile_block = new TurnstileBlock(Material.IRON);
		ticket_machine = new TicketMachine(Material.IRON);
		glass_fence = new GlassFenceBlock();
		noise_barrier = new NoiseBarrierBlock(false);
		noise_barrier_with_lamp = new NoiseBarrierBlock(true);
		slim_passenger_detector = new SlimPassengerDetector(Material.IRON);
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
		GameRegistry.registerBlock(ticket_machine, ticket_machine.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(glass_fence, glass_fence.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(noise_barrier, noise_barrier.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(noise_barrier_with_lamp, noise_barrier_with_lamp.getUnlocalizedName().substring(5));
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
		registerRender(ticket_machine);
		registerRender(glass_fence);
		registerRender(noise_barrier);
		registerRender(noise_barrier_with_lamp);
		registerRender(slim_passenger_detector);
	}
	
	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(References.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
