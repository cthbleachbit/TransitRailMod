package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.prototype.ArrowSign;

public class HungArrowSign extends ArrowSign {
		
	public HungArrowSign(Material materialIn) {
		super(Material.IRON);
		this.setUnlocalizedName("hung_arrow_sign");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
	}
	
	// Properties
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	// Appearance
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = (EnumFacing) state.getValue(FACING);
		if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
			return new AxisAlignedBB(0.0F, 0.5F, 0.4375F, 1.0F, 1.0F, 0.5625F);
		} else {
			return new AxisAlignedBB(0.4375F, 0.5F, 0.0F, 0.5625F, 1.0F, 1.0F);
		}
	}
}
