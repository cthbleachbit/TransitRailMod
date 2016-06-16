package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class PlatformGateBlock extends PlatformBlock {
	
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	public static final PropertyBool LEFT = PropertyBool.create("left"); 
	
	public PlatformGateBlock(Material materialIn) {
		super(Material.glass);
		this.setUnlocalizedName("platform_gate_block");
		this.setDefaultState(getDefaultState()
				.withProperty(UPPER, false)
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(POWERED, false)
				.withProperty(LEFT, false));
	}
	
	// Properties
	public boolean isUpper(IBlockAccess worldIn, BlockPos pos){
		return worldIn.getBlockState(pos.down()).getBlock().equals(this);
	}
	
	public boolean isLeft(IBlockAccess worldIn, BlockPos pos, EnumFacing direc){
		return worldIn.getBlockState(pos.offset(direc.rotateY())).getBlock().equals(this);
	}
	
	// BlockStates
	@Override
	public BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING, UPPER, POWERED, LEFT});
	}
	
	// meta: 0211
	// 2: powered
	// 11 : facing
	@Override
	public int getMetaFromState(IBlockState state) {
		int mFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int mPowered = (Boolean) state.getValue(POWERED) ? 1 : 0;
		return mPowered *4 + mFacing;
	}
	
	// meta: 0211
	// 2: powered
	// 11 : facing
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing pFacing = EnumFacing.getHorizontal(meta % 4); 
		boolean pPowered = meta / 4 == 1;
		return this.getDefaultState().withProperty(FACING, pFacing).withProperty(POWERED, pPowered);
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing direc = (EnumFacing) state.getValue(FACING);
		return state.withProperty(UPPER, isUpper(worldIn, pos)).withProperty(LEFT, isLeft(worldIn, pos, direc));
	}
		
}
