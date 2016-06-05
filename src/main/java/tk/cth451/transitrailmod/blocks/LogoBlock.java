package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tk.cth451.transitrailmod.TransitRailMod;

public class LogoBlock extends Block {

	public LogoBlock(Material materialIn) {
		super(materialIn);
		setHardness(1.5F);
		setResistance(50);
		setLightLevel(1F);
		setUnlocalizedName("logo_block");
		setHarvestLevel("pickaxe", 1);
		setCreativeTab(TransitRailMod.tabTransitRail);
	}

}
