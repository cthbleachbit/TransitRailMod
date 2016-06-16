package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;

public class PlatformGateBlock extends PlatformBlock {
	
	public PlatformGateBlock() {
		super(Material.glass);
		this.setUnlocalizedName("platform_panel_block");
		this.setDefaultState(getDefaultState().withProperty(UPPER, false).withProperty(FACING, EnumFacing.NORTH));
	}
}
