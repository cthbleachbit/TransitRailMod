package tk.cth451.transitrailmod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipe {
	public static void addItemsRecipe() {
		// style changer
		GameRegistry.addRecipe(new ItemStack(ModItems.style_changer),
				"BBB",
				" A ",
				" A ",
				'A', Items.stick,
				'B', Items.paper);
		
		// closed platform door
		GameRegistry.addRecipe(new ItemStack(ModItems.closed_platform_door_item, 16),
				"BCB",
				"BAB",
				"BAB",
				'A', new ItemStack(Blocks.stained_glass, 1, 0),
				'B', Items.iron_ingot,
				'C', Items.redstone);
		
		// closed platform panel
		GameRegistry.addRecipe(new ItemStack(ModItems.closed_platform_panel_item, 16),
				"BBB",
				"BAB",
				"BAB",
				'A', new ItemStack(Blocks.stained_glass, 1, 0),
				'B', Items.iron_ingot);
		
		// platform gate
		GameRegistry.addRecipe(new ItemStack(ModItems.platform_gate_item, 16),
				"   ",
				"BAB",
				"BAB",
				'A', Blocks.glass,
				'B', Items.iron_ingot);
		
		// platform panel
		GameRegistry.addRecipe(new ItemStack(ModItems.platform_panel_item, 16),
				" C ",
				"BAB",
				"BAB",
				'A', Blocks.glass,
				'B', Items.iron_ingot,
				'C', Items.redstone);
	}
	
	public static void addBlocksRecipe(){
		// logo block
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.logo_block, 2),
				new Object[] {Blocks.iron_block, Items.paper});
		
		// hung arrow sign
		GameRegistry.addRecipe(new ItemStack(ModBlocks.hung_arrow_sign, 2),
				"BBB",
				" A ",
				"   ",
				'A', Items.arrow,
				'B', Items.iron_ingot);
		
		// platform arrow sign
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.platform_arrow_sign, 2),
				new Object[] {Blocks.iron_block, Items.arrow});
		
		// fluorescent lamp
		GameRegistry.addRecipe(new ItemStack(ModBlocks.fluorescent_lamp, 16),
				" A ",
				" B ",
				" C ",
				'A', Blocks.glass,
				'B', Blocks.glowstone,
				'C', Blocks.iron_block);
		
		// wire panel
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wire_panel, 8),
				"ABA",
				"ABA",
				"ABA",
				'A', Items.iron_ingot,
				'B', Items.redstone);
		
		// wire panel corner, 1 to 1 conversion with wire panel
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.wire_panel_corner),
				new Object[] {ModBlocks.wire_panel});
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.wire_panel),
				new Object[] {ModBlocks.wire_panel_corner});
	}
}
