package tk.cth451.transitrailmod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipe {
	public static void addItemsRecipe() {
		// style changer
		GameRegistry.addRecipe(new ItemStack(ModItems.style_changer),
				"BBB",
				" A ",
				" A ",
				'A', Items.STICK,
				'B', Items.PAPER);
		
		// closed platform door
		GameRegistry.addRecipe(new ItemStack(ModItems.closed_platform_door_item, 16),
				"BCB",
				"BAB",
				"BAB",
				'A', Blocks.GLASS,
				'B', Items.IRON_INGOT,
				'C', Items.REDSTONE);
		
		// closed platform panel
		GameRegistry.addRecipe(new ItemStack(ModItems.closed_platform_panel_item, 16),
				"BBB",
				"BAB",
				"BAB",
				'A', Blocks.GLASS,
				'B', Items.IRON_INGOT);
		
		// platform gate
		GameRegistry.addRecipe(new ItemStack(ModItems.platform_gate_item, 16),
				"   ",
				"BAB",
				"BAB",
				'A', Blocks.GLASS,
				'B', Items.IRON_INGOT);
		GameRegistry.addRecipe(new ItemStack(ModItems.platform_gate_item, 16),
				"BAB",
				"BAB",
				"   ",
				'A', Blocks.GLASS,
				'B', Items.IRON_INGOT);
		
		// platform panel
		GameRegistry.addRecipe(new ItemStack(ModItems.platform_panel_item, 16),
				" C ",
				"BAB",
				"BAB",
				'A', Blocks.GLASS,
				'B', Items.IRON_INGOT,
				'C', Items.REDSTONE);
	}
	
	public static void addBlocksRecipe(){
		// logo block
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.logo_block, 2),
				new Object[] {Blocks.IRON_BLOCK, Items.PAPER});
		
		// hung arrow sign
		GameRegistry.addRecipe(new ItemStack(ModBlocks.hung_arrow_sign, 2),
				"   ",
				"BBB",
				" A ",
				'A', Items.ARROW,
				'B', Items.IRON_INGOT);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.hung_arrow_sign, 2),
				"BBB",
				" A ",
				"   ",
				'A', Items.ARROW,
				'B', Items.IRON_INGOT);
		
		// platform arrow sign
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.platform_arrow_sign, 2),
				new Object[] {Blocks.IRON_BLOCK, Items.ARROW});
		
		// fluorescent lamp
		GameRegistry.addRecipe(new ItemStack(ModBlocks.fluorescent_lamp, 16),
				"A  ",
				"B  ",
				"C  ",
				'A', Blocks.GLASS,
				'B', Blocks.GLOWSTONE,
				'C', Blocks.IRON_BLOCK);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.fluorescent_lamp, 16),
				" A ",
				" B ",
				" C ",
				'A', Blocks.GLASS,
				'B', Blocks.GLOWSTONE,
				'C', Blocks.IRON_BLOCK);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.fluorescent_lamp, 16),
				"  A",
				"  B",
				"  C",
				'A', Blocks.GLASS,
				'B', Blocks.GLOWSTONE,
				'C', Blocks.IRON_BLOCK);
		
		// wire panel
		GameRegistry.addRecipe(new ItemStack(ModBlocks.wire_panel, 8),
				"ABA",
				"ABA",
				"ABA",
				'A', Items.IRON_INGOT,
				'B', Items.REDSTONE);
		
		// wire panel corner, 1 to 1 conversion with wire panel
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.wire_panel_corner),
				new Object[] {ModBlocks.wire_panel});
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.wire_panel),
				new Object[] {ModBlocks.wire_panel_corner});
		
		// turnstile block
		GameRegistry.addRecipe(new ItemStack(ModBlocks.turnstile_block, 8),
				"AAB",
				"  A",
				"  A",
				'A', Items.IRON_INGOT,
				'B', Items.REDSTONE);
		
		// glass fence
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glass_fence, 8),
				"   ",
				"AAA",
				"ABA",
				'A', Items.IRON_INGOT,
				'B', Blocks.GLASS);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glass_fence, 8),
				"AAA",
				"ABA",
				"   ",
				'A', Items.IRON_INGOT,
				'B', Blocks.GLASS);
		
		// noise barrier
		GameRegistry.addRecipe(new ItemStack(ModBlocks.noise_barrier, 8),
				"ABA",
				"ABA",
				"ABA",
				'A', Items.IRON_INGOT,
				'B', Blocks.GLASS);
		
		// noise barrier with light
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.noise_barrier_with_lamp, 1),
				new Object[] {ModBlocks.noise_barrier, ModBlocks.fluorescent_lamp});
		
		// slim passenger detector
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.slim_passenger_detector, 4),
				new Object[] {Items.IRON_INGOT, Items.REDSTONE, Blocks.GLASS});
	}
}
