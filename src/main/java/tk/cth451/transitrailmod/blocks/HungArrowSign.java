package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.enums.EnumArrow;

public class HungArrowSign extends Block {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum ARROW = PropertyEnum.create("arrow", EnumArrow.class);
	
	public HungArrowSign() {
		super(Material.iron);
		this.setLightLevel(1.0F);
		this.setUnlocalizedName("hung_icon_sign");
		this.setDefaultState(getDefaultState()
				.withProperty(ARROW, EnumArrow.ARROW_UP)
				.withProperty(FACING, EnumFacing.NORTH));
	}
	
	// Block state
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {ARROW, FACING});
	}
	
	// meta 1122
	// 11: ARROW
	// 22: FACING
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing pFacing = EnumFacing.getHorizontal(meta % 4);
		EnumArrow pArrow = EnumArrow.fromMeta(meta / 4);
		return this.getDefaultState().withProperty(ARROW, pArrow).withProperty(FACING, pFacing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int mFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int mArrow = ((EnumArrow) state.getValue(ARROW)).toMeta();
		return mArrow * 4 + mFacing;
	}
	
	// Properties
	@Override
	public boolean isTranslucent() {
		return true;
	}
	
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}
	
	@Override
	public int getMobilityFlag()
    {
        return 1;
    }
	
	@Override
	public boolean isOpaqueCube() {
        return false;
    }
	
	@Override
	public boolean isFullCube()
    {
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}
}
