package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;
import tk.cth451.transitrailmod.TransitRailMod;

public class ClosedPlatformPanelBlock extends Block{
	public ClosedPlatformPanelBlock(Material materialIn) {
		super(Material.glass);
		this.setUnlocalizedName("closed_platform_panel_block");
	}
	
	@Override
	public boolean isOpaqueCube() {
        return false;
    }
	
	@Override
	public boolean isFullCube() {
		return false;
	}
	
	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}
}
