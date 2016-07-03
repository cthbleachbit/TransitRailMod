package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.material.Material;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.prototype.ArrowSign;

public class PlatformArrowSign extends ArrowSign {
	
	public PlatformArrowSign(Material materialIn) {
		super(Material.iron);
		this.setUnlocalizedName("platform_arrow_sign");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
	}
}
