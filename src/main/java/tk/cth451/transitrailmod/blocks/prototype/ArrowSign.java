package tk.cth451.transitrailmod.blocks.prototype;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.enums.EnumArrow;

public abstract class ArrowSign extends CustomDirectionBlock {
	
	public static final PropertyEnum ARROW = PropertyEnum.create("arrow", EnumArrow.class);
	
	public ArrowSign(Material materialIn) {
		super(Material.IRON);
		this.setLightLevel(1.0F);
		this.setDefaultState(getDefaultState()
				.withProperty(ARROW, EnumArrow.ARROW_UP)
				.withProperty(FACING, EnumFacing.NORTH));
	}
	
	// Block state
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ARROW, FACING});
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
	public boolean isTranslucent(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	// Interactions
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
		// set facing to the direction player is facing
		IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		return this.getFacingState(state, placer);
	}
	
	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
}
