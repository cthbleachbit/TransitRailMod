package tk.cth451.transitrailmod.blocks;

import java.util.List;
import java.util.Random;

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
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.blocks.prototype.ClosedPlatformBlock;

public class ClosedPlatformTop extends ClosedPlatformBlock{
	
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	
	public ClosedPlatformTop(Material materialIn) {
		super(Material.iron);
		this.setLightLevel(1F);
		// Full brightness
		this.setUnlocalizedName("closed_platform_top");
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false));
	}
	
	// Properties
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
		EnumFacing facing = (EnumFacing) worldIn.getBlockState(pos).getValue(FACING);
		if (facing == EnumFacing.NORTH) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
		} else if (facing == EnumFacing.EAST) {
			this.setBlockBounds(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else if (facing == EnumFacing.SOUTH) {
			this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
		} else if (facing == EnumFacing.WEST) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
		}
    }
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list,
			Entity collidingEntity) {
		this.setBlockBoundsBasedOnState(worldIn, pos);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.SOLID;
	}
	
	@Override
	public boolean canProvidePower() {
		// TODO Auto-generated method stub
		return super.canProvidePower();
	}
	
	// Block state related
	// meta: 0211
	// 2: powered
	// 11 : facing
	@Override
	public int getMetaFromState(IBlockState state) {
		int mFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int mPowered = (Boolean) state.getValue(POWERED) ? 1 : 0;
		return mPowered *4 + mFacing;
	}
	
	@Override
	public BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING, POWERED});
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
	
	// Interactions
	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		// TODO: A dirty hack here.
		boolean isCreative = player.capabilities.isCreativeMode;
		world.destroyBlock(pos.down(), !isCreative);
		world.setBlockToAir(pos.down(2));
		world.setBlockToAir(pos);
		return true;
	}
	
	// Redstone
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		Boolean flag = worldIn.isBlockPowered(pos);
		if (flag != (Boolean) state.getValue(POWERED)) {
			worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(flag)), 2);
			worldIn.markBlockRangeForRenderUpdate(pos, pos);
		}
		super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
		if (side == EnumFacing.DOWN) {
			return (Boolean) state.getValue(POWERED) ? 15 : 0;
		} else {
			return 0;
		}
	}
}
