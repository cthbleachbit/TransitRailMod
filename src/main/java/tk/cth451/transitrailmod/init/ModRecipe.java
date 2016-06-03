package tk.cth451.transitrailmod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipe {
	public static void addRecipe()
	{
		GameRegistry.addRecipe(new ItemStack(Blocks.obsidian),
				"AAA",
				"AAA",
				"   ",
				'A', Items.apple);
	}
}
