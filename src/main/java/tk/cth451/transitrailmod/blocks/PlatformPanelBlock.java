package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;

public class PlatformPanelBlock extends PlatformBlock {
	
	public PlatformPanelBlock(Material materialIn) {
		super(Material.glass);
		this.setUnlocalizedName("platform_panel_block");
		this.setDefaultState(getDefaultState().withProperty(UPPER, false).withProperty(FACING, EnumFacing.NORTH));
	}
}
