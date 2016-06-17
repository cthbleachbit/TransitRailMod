package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class PlatformPanelBlock extends PlatformBlock {
	
	public PlatformPanelBlock(Material materialIn) {
		super(Material.iron);
		this.setUnlocalizedName("platform_panel_block");
		this.setDefaultState(getDefaultState().withProperty(UPPER, false).withProperty(FACING, EnumFacing.NORTH));
	}
	
	// Properties
	public boolean isUpper(IBlockAccess worldIn, BlockPos pos){
		return worldIn.getBlockState(pos.down()).getBlock().equals(this);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
		EnumFacing facing = (EnumFacing) worldIn.getBlockState(pos).getValue(FACING);
		if (isUpper(worldIn, pos)) {
			if (facing == EnumFacing.NORTH) {
				this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 0.5F, 0.25F);
			} else if (facing == EnumFacing.EAST) {
				this.setBlockBounds(0.75F, 0.0F, 0.0F, 0.875F, 0.5F, 1.0F);
			} else if (facing == EnumFacing.SOUTH) {
				this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 0.5F, 0.875F);
			} else if (facing == EnumFacing.WEST) {
				this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.25F, 0.5F, 1.0F);
			}
		} else {
			if (facing == EnumFacing.NORTH) {
				this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.25F);
			} else if (facing == EnumFacing.EAST) {
				this.setBlockBounds(0.75F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
			} else if (facing == EnumFacing.SOUTH) {
				this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 0.875F);
			} else if (facing == EnumFacing.WEST) {
				this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
			}
		}
    }
	
	// Blockstates
	@Override
	public BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING, UPPER});
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(UPPER, isUpper(worldIn, pos));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}
}
