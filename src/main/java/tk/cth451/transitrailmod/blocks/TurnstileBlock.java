package tk.cth451.transitrailmod.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.TransitRailTab;
import tk.cth451.transitrailmod.blocks.prototype.CustomDirectionBlock;
import tk.cth451.transitrailmod.enums.EnumPassingDirection;
import tk.cth451.transitrailmod.init.ModItems;

public class TurnstileBlock extends CustomDirectionBlock{
	
	public static final PropertyBool ACTIVE = PropertyBool.create("active");
	// whether the turnstile has processed the ticket and open the gate
	public static final PropertyEnum PASSING = PropertyEnum.create("passing", EnumPassingDirection.class);
	
	public TurnstileBlock(Material materialIn) {
		super(Material.iron);
		this.setUnlocalizedName("turnstile_block");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
		this.setTickRandomly(true);
		this.setDefaultState(this.getDefaultState()
				.withProperty(ACTIVE, false)
				.withProperty(PASSING, EnumPassingDirection.INSIDE)
				.withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public boolean isTranslucent() {
		return true;
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
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
	
	@Override
	public int tickRate(World worldIn) {
		return 20;
	}
	
	// Block State
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {ACTIVE, PASSING, FACING});
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
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.SOLID;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		switch (((EnumFacing) state.getValue(FACING))) {
			case NORTH :
				this.setBlockBounds(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				break;
			case EAST :
				this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
				break;
			case SOUTH :
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
				break;
			default : //WEST
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
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
	
	private AxisAlignedBB getBBFromBounds (BlockPos pos, double x1, double y1, double z1, double x2, double y2, double z2) {
		return new AxisAlignedBB(pos.getX() + x1, pos.getY() + y1, pos.getZ() + z1, pos.getX() + x2, pos.getY() + y2, pos.getZ() + z2);
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list,
			Entity collidingEntity) {
		this.setBlockBoundsBasedOnState(worldIn, pos);
		AxisAlignedBB axisalignedbb1 = this.getCollisionBoundingBox(worldIn, pos, state);
		AxisAlignedBB barBound = this.getBarBoundsBasedOnState(worldIn, pos);
		if (axisalignedbb1 != null && mask.intersectsWith(axisalignedbb1)) {
			list.add(axisalignedbb1);
		}
		if (barBound != null && mask.intersectsWith(barBound)) {
			list.add(barBound);
		}
	}
}
