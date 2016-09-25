package tk.cth451.transitrailmod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.blocks.prototype.ClosedPlatformBlock;
import tk.cth451.transitrailmod.init.ModItems;

public class ClosedPlatformDoorBlock extends ClosedPlatformBlock {
	
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	public static final PropertyBool LEFT = PropertyBool.create("left"); 
	
	public ClosedPlatformDoorBlock(Material materialIn) {
		super(Material.GLASS);
		this.setUnlocalizedName("closed_platform_door_block");
		this.setDefaultState(getDefaultState()
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(UPPER, false)
				.withProperty(POWERED, true)
				.withProperty(LEFT, false));
	}
	
	// Properties
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = (EnumFacing) state.getValue(FACING);
		boolean isOpen = (Boolean) state.getValue(POWERED);
		boolean leftOrNot = this.isLeft(source, pos, facing);
		if (!isOpen) {
			if (facing == EnumFacing.NORTH) {
				return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
			} else if (facing == EnumFacing.EAST) {
				return new AxisAlignedBB(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			} else if (facing == EnumFacing.SOUTH) {
				return new AxisAlignedBB(0.0F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
			} else { // if (facing == EnumFacing.WEST) 
				return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.125F, 1.0F, 1.0F);
			}
		} else if (leftOrNot) {
			if (facing == EnumFacing.NORTH) {
				return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.125F, 1.0F, 0.125F);
			} else if (facing == EnumFacing.EAST) {
				return new AxisAlignedBB(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
			} else if (facing == EnumFacing.SOUTH) {
				return new AxisAlignedBB(0.875F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
			} else { // if (facing == EnumFacing.WEST) 
				return new AxisAlignedBB(0.0F, 0.0F, 0.875F, 0.125F, 1.0F, 1.0F);
			}
		} else {
			if (facing == EnumFacing.NORTH) {
				return new AxisAlignedBB(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
			} else if (facing == EnumFacing.EAST) {
				return new AxisAlignedBB(0.875F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
			} else if (facing == EnumFacing.SOUTH) {
				return new AxisAlignedBB(0.0F, 0.0F, 0.875F, 0.125F, 1.0F, 1.0F);
			} else  { // if (facing == EnumFacing.WEST) 
				return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.125F, 1.0F, 0.125F);
			}
		}
	}
	
	// BlockStates
	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING, UPPER, POWERED, LEFT});
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing direc = (EnumFacing) state.getValue(FACING);
		return state.withProperty(UPPER, isUpper(worldIn, pos)).withProperty(LEFT, isLeft(worldIn, pos, direc));
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
	
	public boolean isLeft(IBlockAccess worldIn, BlockPos pos, EnumFacing direc){
		return worldIn.getBlockState(pos.offset(direc.rotateY())).getBlock().equals(this);
	}
	
	// Interactions	
	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos) {
		return this.getItem();
	}
	
	private Item getItem() {
		return ModItems.closed_platform_door_item;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)	{
		return this.getItem();
	}
	
	// Redstone
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		boolean flagUpper = isUpper(worldIn, pos);
		// check whether the block triggered is the upper part
		
		BlockPos pos1 = flagUpper ? pos.down() : pos.up();
		// get the other part
		
		Boolean flagOkay = (worldIn.getBlockState(pos1).getBlock() == this);
		// check whether the other part gets destroyed
		if (flagOkay) {
			// if not triggered by player breaking parts, update redstone status
			Boolean flag = (Boolean) worldIn.getBlockState(pos.up()).getValue(POWERED);
			if (flag != (Boolean) state.getValue(POWERED)) {
				worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(flag)), 2);
				worldIn.markBlockRangeForRenderUpdate(pos, pos);
			}
			super.neighborChanged(state, worldIn, pos1, blockIn);
		} else {
			this.destroyParts(flagUpper, worldIn, pos);
		}
	}
}
