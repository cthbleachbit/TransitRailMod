package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class ClosedPlatformPanelBlock extends Block{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool UPPER = PropertyBool.create("upper");
	
	public ClosedPlatformPanelBlock(Material materialIn) {
		super(Material.glass);
		this.setUnlocalizedName("closed_platform_panel_block");
		this.setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(UPPER, false));
	}
	
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING, UPPER});
	}
	
	@Override
	public boolean isOpaqueCube() {
        return false;
    }
	
	@Override
	public boolean isSolidFullCube()
    {
        return false;
    }
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
		// set facing to the direction player is facing
		IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		if (state.getValue(UPPER).equals(false)) {
			worldIn.setBlockState(pos.up(), state.withProperty(UPPER, true).withProperty(FACING, placer.getHorizontalFacing()));
			return state.withProperty(UPPER, false).withProperty(FACING, placer.getHorizontalFacing());
		} else {
			return state.withProperty(UPPER, true).withProperty(FACING, placer.getHorizontalFacing());
		}
	}
	
	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int keyFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int keyUpper = state.getValue(UPPER).equals(true) ? 1 : 0;
		return keyFacing * 2 + keyUpper;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int keyFacing = meta / 2;
		int keyUpper = meta % 2;
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(keyFacing)).withProperty(UPPER, keyUpper==1);
	}
}
