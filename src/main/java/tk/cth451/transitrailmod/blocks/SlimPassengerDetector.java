package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import tk.cth451.transitrailmod.blocks.prototype.EntityDetector;

public class SlimPassengerDetector extends EntityDetector {
	
	public SlimPassengerDetector(Material materialIn) {
		super(Material.GLASS);
		this.setUnlocalizedName("slim_passenger_detector");
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch ((EnumFacing) source.getBlockState(pos).getValue(FACING)) {
		case NORTH:
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.1875F, 0.0625F);
		case EAST:
			return new AxisAlignedBB(0.9375F, 0.0F, 0.0F, 1.0F, 0.1875F, 1.0F);
		case SOUTH:
			return new AxisAlignedBB(0.0F, 0.0F, 0.9375F, 1.0F, 0.1875F, 1.0F);
		default: // west
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.0625F, 0.1875F, 1.0F);
		}
	}
}
