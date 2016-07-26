package tk.cth451.transitrailmod.blocks.prototype;

import java.util.List;
import java.util.Random;

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

public abstract class EntityDetector extends CustomDirectionBlock {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	
	public EntityDetector(Material materialIn) {
		super(materialIn);
		this.setTickRandomly(true);
		this.setCreativeTab(TransitRailMod.tabTransitRail);
		this.setDefaultState(getDefaultState()
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(POWERED, false));
	}
	
	// Block State
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING, POWERED});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int mFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int mPowered = ((Boolean) state.getValue(POWERED)) ? 1 : 0;
		return mPowered * 4 + mFacing;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing pFacing = EnumFacing.getHorizontal(meta % 4);
		boolean pPowered = (meta / 4) > 0;
		return getDefaultState()
				.withProperty(FACING, pFacing)
				.withProperty(POWERED, pPowered);
	}
	
	// Properties
	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
	
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        return null;
    }
	
	@Override
	public boolean canProvidePower() {
		return true;
	}
	
	@Override
	public int tickRate(World worldIn) {
		return 20;
	}
	
	// Interactions
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote){
			this.updateState(state, worldIn, pos);
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!worldIn.isRemote){
			this.updateState(state, worldIn, pos);
		}
	}
	
	protected void updateState(IBlockState state, World worldIn, BlockPos pos) {
		worldIn.setBlockState(pos, state.withProperty(POWERED, givePower(worldIn, pos) > 0));
		EnumFacing facing = (EnumFacing) worldIn.getBlockState(pos).getValue(FACING);
		worldIn.notifyNeighborsOfStateChange(pos.offset(facing), this);
	}
	
	protected AxisAlignedBB getSpaceToCheck(World worldIn, BlockPos pos){
		return new AxisAlignedBB(
				pos.getX(),
				pos.getY(),
				pos.getZ(),
				pos.getX() + 1,
				pos.getY() + 1,
				pos.getZ() + 1);
	}
	
	protected int givePower(World worldIn, BlockPos pos){
		AxisAlignedBB spaceToCheck = getSpaceToCheck(worldIn, pos);
		List list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, spaceToCheck);
		
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
		return list.isEmpty() ? 0 : 15;
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
		return side.getOpposite() == (EnumFacing) state.getValue(FACING) ? givePower((World) worldIn, pos) : 0;
	}
	
	@Override
	public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
		return this.isProvidingWeakPower(worldIn, pos, state, side);
	}
}
