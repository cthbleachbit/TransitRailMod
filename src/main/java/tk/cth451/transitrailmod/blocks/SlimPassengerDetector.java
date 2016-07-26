package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import tk.cth451.transitrailmod.blocks.prototype.EntityDetector;

public class SlimPassengerDetector extends EntityDetector{
	
	public SlimPassengerDetector(Material materialIn) {
		super(Material.glass);
		this.setUnlocalizedName("slim_passenger_detector");
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
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		switch ((EnumFacing) worldIn.getBlockState(pos).getValue(FACING)) {
			case NORTH:
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.1875F, 0.0625F);
				break;
			case EAST:
				this.setBlockBounds(0.9375F, 0.0F, 0.0F, 1.0F, 0.1875F, 1.0F);
				break;
			case SOUTH:
				this.setBlockBounds(0.0F, 0.0F, 0.9375F, 1.0F, 0.1875F, 1.0F);
				break;
			default: // west
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0625F, 0.1875F, 1.0F);
				break;
		}
	}
}
