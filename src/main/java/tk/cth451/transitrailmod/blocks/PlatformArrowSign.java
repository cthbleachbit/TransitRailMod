package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.prototype.ArrowSign;

public class PlatformArrowSign extends ArrowSign {
	
	public PlatformArrowSign(Material materialIn) {
		super(Material.iron);
		this.setUnlocalizedName("platform_arrow_sign");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
	}
}
