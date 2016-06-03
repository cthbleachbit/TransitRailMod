package tk.cth451.transitrailmod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.cth451.transitrailmod.References;
import tk.cth451.transitrailmod.items.ClosedPlatformDoorItem;
import tk.cth451.transitrailmod.items.ClosedPlatformPanelItem;
import tk.cth451.transitrailmod.items.PlatformGateItem;
import tk.cth451.transitrailmod.items.PlatformPanelItem;
import tk.cth451.transitrailmod.items.StyleChanger;
import tk.cth451.transitrailmod.items.TrainTicket;
import tk.cth451.transitrailmod.items.TicketMachineItem;
import tk.cth451.transitrailmod.items.TicketingGateItem;

public class ModItems {
	public static Item style_changer;
	public static Item closed_platform_door_item;
	public static Item closed_platform_panel_item;
	public static Item platform_gate_item;
	public static Item platform_panel_item;
	public static Item ticketing_gate_item;
	public static Item train_ticket;
	public static Item ticket_machine_item;
	public static Item[] listOfItem = {
		style_changer,
		closed_platform_door_item,
		closed_platform_panel_item,
		platform_gate_item,
		platform_panel_item,
		ticketing_gate_item,
		train_ticket,
		ticket_machine_item
	};
	
	public static void init(){
		style_changer = new StyleChanger();
		closed_platform_door_item = new ClosedPlatformDoorItem();
		closed_platform_panel_item = new ClosedPlatformPanelItem();
		platform_gate_item = new PlatformGateItem();
		platform_panel_item = new PlatformPanelItem();
		ticketing_gate_item = new TicketingGateItem();
		train_ticket = new TrainTicket();
		ticket_machine_item = new TicketMachineItem();
	}
	
	public static void register(){
		GameRegistry.registerItem(style_changer, style_changer.getUnlocalizedName().substring(5)); 
		GameRegistry.registerItem(closed_platform_door_item, closed_platform_door_item.getUnlocalizedName().substring(5)); 
		GameRegistry.registerItem(closed_platform_panel_item, closed_platform_panel_item.getUnlocalizedName().substring(5)); 
		GameRegistry.registerItem(platform_gate_item, platform_gate_item.getUnlocalizedName().substring(5)); 
		GameRegistry.registerItem(platform_panel_item, platform_panel_item.getUnlocalizedName().substring(5)); 
		GameRegistry.registerItem(ticketing_gate_item, ticketing_gate_item.getUnlocalizedName().substring(5)); 
		GameRegistry.registerItem(train_ticket, train_ticket.getUnlocalizedName().substring(5)); 
		GameRegistry.registerItem(ticket_machine_item, ticket_machine_item.getUnlocalizedName().substring(5)); 
	}
	
	public static void registerRenders(){
		registerRender(style_changer);
		registerRender(closed_platform_door_item);
		registerRender(closed_platform_panel_item);
		registerRender(platform_gate_item);
		registerRender(platform_panel_item);
		registerRender(ticketing_gate_item);
		registerRender(train_ticket);
		registerRender(ticket_machine_item);
	}
	
	public static void registerRender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(References.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
