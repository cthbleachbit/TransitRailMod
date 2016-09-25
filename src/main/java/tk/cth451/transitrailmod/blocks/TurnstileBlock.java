package tk.cth451.transitrailmod.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.prototype.CustomDirectionBlock;
import tk.cth451.transitrailmod.enums.EnumPassingDirection;

public class TurnstileBlock extends CustomDirectionBlock{
	
	public static final PropertyBool ACTIVE = PropertyBool.create("active");
	// whether the turnstile has processed the ticket and open the gate
	public static final PropertyEnum PASSING = PropertyEnum.create("passing", EnumPassingDirection.class);
	
	public TurnstileBlock(Material materialIn) {
		super(Material.IRON);
		this.setUnlocalizedName("turnstile_block");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
		this.setTickRandomly(true);
		this.setDefaultState(this.getDefaultState()
				.withProperty(ACTIVE, false)
				.withProperty(PASSING, EnumPassingDirection.INSIDE)
				.withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public boolean isTranslucent(IBlockState state) {
		return false;
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
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
	
	@Override
	public int tickRate(World worldIn) {
		return 20;
	}
	
	// Block State
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ACTIVE, PASSING, FACING});
	}
	
	// meta: 3211
	// 11: facing
	// 2: active
	// 3: passing
	@Override
	public int getMetaFromState(IBlockState state) {
		int mFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int mActive = (Boolean) state.getValue(ACTIVE) ? 1 : 0;
		int mPassing = ((EnumPassingDirection) state.getValue(PASSING)).toMeta();
		return mPassing * 8 + mActive * 4 + mFacing;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing pFacing = EnumFacing.getHorizontal(meta % 4);
		boolean pActive = (meta % 8) / 4 > 0;
		EnumPassingDirection pPassing = EnumPassingDirection.fromMeta(meta / 8);
		return getDefaultState()
				.withProperty(ACTIVE, pActive)
				.withProperty(PASSING, pPassing)
				.withProperty(FACING, pFacing);
	}
	
	// Interactions
	
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if ((Boolean) state.getValue(ACTIVE)){
			worldIn.setBlockState(pos, state.withProperty(ACTIVE, false));
		}
	}
	
	// Appearance
	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		switch (((EnumFacing) state.getValue(FACING))) {
			case NORTH :
				return new AxisAlignedBB(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			case EAST :
				return new AxisAlignedBB(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
			case SOUTH :
				return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
			default : //WEST
				return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!worldIn.isRemote){
			AxisAlignedBB spaceToCheck = getSpaceToCheck(worldIn, pos);
			List list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, spaceToCheck);
			
			worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
			if (list.isEmpty()){
				if ((Boolean) state.getValue(ACTIVE)){
					worldIn.setBlockState(pos, state.withProperty(ACTIVE, false));
				}
			}
		}
	}
	
	protected AxisAlignedBB getBarBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		if ((Boolean) state.getValue(ACTIVE)){
			return null;
		} else {
			switch (((EnumFacing) state.getValue(FACING))) {
				case NORTH :
					return getBBFromBounds(pos, 0.0F, 0.4375F, 0.4375F, 1.0F, 1.5F, 0.5625F);
				case SOUTH :
					return getBBFromBounds(pos, 0.0F, 0.4375F, 0.4375F, 1.0F, 1.5F, 0.5625F);
				default : // EAST WEST
					return getBBFromBounds(pos, 0.4375F, 0.4375F, 0.0F, 0.5625F, 1.5F, 1.0F);
			}
		}
	}
	
	// extra bounding box on top of side panel
	protected AxisAlignedBB getSideBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		switch (((EnumFacing) state.getValue(FACING))) {
			case NORTH :
				return getBBFromBounds(pos, 0.75F, 1.0F, 0.0F, 1.0F, 1.5F, 1.0F);
			case EAST :
				return getBBFromBounds(pos, 0.0F, 1.0F, 0.75F, 1.0F, 1.5F, 1.0F);
			case SOUTH :
				return getBBFromBounds(pos, 0.0F, 1.0F, 0.0F, 0.25F, 1.5F, 1.0F);
			default : // WEST
				return getBBFromBounds(pos, 0.0F, 1.0F, 0.0F, 1.0F, 1.5F, 0.25F);
		}
	}
	
	private AxisAlignedBB getBBFromBounds (BlockPos pos, double x1, double y1, double z1, double x2, double y2, double z2) {
		return new AxisAlignedBB(pos.getX() + x1, pos.getY() + y1, pos.getZ() + z1, pos.getX() + x2, pos.getY() + y2, pos.getZ() + z2);
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
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return this.getSelectedBoundingBox(state, worldIn, pos);
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn);
		AxisAlignedBB axisalignedbb1 = this.getCollisionBoundingBox(state, worldIn, pos);
		AxisAlignedBB barBound = this.getBarBoundsBasedOnState(worldIn, pos);
		AxisAlignedBB sideBound = this.getSideBoundsBasedOnState(worldIn, pos);
		collidingBoxes.add(barBound);
		collidingBoxes.add(sideBound);
	}
}
