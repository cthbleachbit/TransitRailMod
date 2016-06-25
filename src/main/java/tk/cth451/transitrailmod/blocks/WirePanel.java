package tk.cth451.transitrailmod.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.prototype.CustomDirectionBlock;
import tk.cth451.transitrailmod.init.ModBlocks;

public class WirePanel extends CustomDirectionBlock {
	
	public static final PropertyBool LAMP = PropertyBool.create("lamp");
	
	public WirePanel(Material materialIn) {
		super(Material.iron);
		this.setUnlocalizedName("wire_panel");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
		this.setDefaultState(this.getDefaultState()
				.withProperty(LAMP, false)
				.withProperty(FACING, EnumFacing.NORTH));
	}
	
	// Properties
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean isFullCube()
    {
        return false;
    }
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		EnumFacing facing = (EnumFacing) worldIn.getBlockState(pos).getValue(FACING); 
		if (facing == EnumFacing.NORTH) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
		} else if (facing == EnumFacing.EAST) {
			this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else if (facing == EnumFacing.SOUTH) {
			this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
		} else { // WEST
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
		}
	}
	
	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
	
	@Override
	public boolean canProvidePower() {
		return true;
	}
	
	// Block States
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING, LAMP});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int mFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int mLamp = (Boolean) state.getValue(LAMP) ? 1 : 0;
		return mLamp * 4 + mFacing;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing pFacing = EnumFacing.getHorizontal(meta % 4);
		boolean pLamp = meta % 4 > 0;
		return this.getDefaultState()
				.withProperty(FACING, pFacing)
				.withProperty(LAMP, pLamp);
	}
	
	// Interactions
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
		// set facing to the direction player is facing
		IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		return this.getFacingState(state, placer)
				.withProperty(LAMP, this.checkLampPresent(worldIn, pos));
	}
	
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		EnumFacing sideToProvide = ((EnumFacing) state.getValue(FACING)).getOpposite();
		worldIn.setBlockState(pos, state.withProperty(LAMP, this.checkLampPresent(worldIn, pos)));
		worldIn.notifyBlockOfStateChange(pos.offset(sideToProvide), this);
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
		if (this.checkLampPresent(worldIn, pos)) {
			EnumFacing sideToProvide = ((EnumFacing) state.getValue(FACING));
			return sideToProvide == side ? 15 : 0;
		} else {
			return 0;
		}
	}
	
	protected boolean checkLampPresent(IBlockAccess worldIn, BlockPos pos){
		return worldIn.getBlockState(pos.up()).getBlock() == ModBlocks.fluorescent_lamp;
	}
}
