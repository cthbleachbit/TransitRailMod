package tk.cth451.transitrailmod.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.init.ModItems;

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
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
		EnumFacing facing = (EnumFacing) worldIn.getBlockState(pos).getValue(FACING);
		boolean isOpen = (Boolean) worldIn.getBlockState(pos).getValue(POWERED);
		boolean leftOrNot = this.isLeft(worldIn, pos, facing);
		if (isUpper(worldIn, pos)) {
			if (!isOpen) {
				if (facing == EnumFacing.NORTH) {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.125F);
				} else if (facing == EnumFacing.EAST) {
					this.setBlockBounds(0.875F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
				} else if (facing == EnumFacing.SOUTH) {
					this.setBlockBounds(0.0F, 0.0F, 0.875F, 1.0F, 0.5F, 1.0F);
				} else if (facing == EnumFacing.WEST) {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.125F, 0.5F, 1.0F);
				}
			} else if (leftOrNot) {
				if (facing == EnumFacing.NORTH) {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.125F, 0.5F, 0.125F);
				} else if (facing == EnumFacing.EAST) {
					this.setBlockBounds(0.875F, 0.0F, 0.0F, 1.0F, 0.5F, 0.125F);
				} else if (facing == EnumFacing.SOUTH) {
					this.setBlockBounds(0.875F, 0.0F, 0.875F, 1.0F, 0.5F, 1.0F);
				} else if (facing == EnumFacing.WEST) {
					this.setBlockBounds(0.0F, 0.0F, 0.875F, 0.125F, 0.5F, 1.0F);
				}
			} else {
				if (facing == EnumFacing.NORTH) {
					this.setBlockBounds(0.875F, 0.0F, 0.0F, 1.0F, 0.5F, 0.125F);
				} else if (facing == EnumFacing.EAST) {
					this.setBlockBounds(0.875F, 0.0F, 0.875F, 1.0F, 0.5F, 1.0F);
				} else if (facing == EnumFacing.SOUTH) {
					this.setBlockBounds(0.0F, 0.0F, 0.875F, 0.125F, 0.5F, 1.0F);
				} else if (facing == EnumFacing.WEST) {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.125F, 0.5F, 0.125F);
				}
			}
		} else {
			if (!isOpen) {
				if (facing == EnumFacing.NORTH) {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
				} else if (facing == EnumFacing.EAST) {
					this.setBlockBounds(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				} else if (facing == EnumFacing.SOUTH) {
					this.setBlockBounds(0.0F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
				} else if (facing == EnumFacing.WEST) {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.125F, 1.0F, 1.0F);
				}
			} else if (leftOrNot) {
				if (facing == EnumFacing.NORTH) {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.125F, 1.0F, 0.125F);
				} else if (facing == EnumFacing.EAST) {
					this.setBlockBounds(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
				} else if (facing == EnumFacing.SOUTH) {
					this.setBlockBounds(0.875F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
				} else if (facing == EnumFacing.WEST) {
					this.setBlockBounds(0.0F, 0.0F, 0.875F, 0.125F, 1.0F, 1.0F);
				}
			} else {
				if (facing == EnumFacing.NORTH) {
					this.setBlockBounds(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
				} else if (facing == EnumFacing.EAST) {
					this.setBlockBounds(0.875F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
				} else if (facing == EnumFacing.SOUTH) {
					this.setBlockBounds(0.0F, 0.0F, 0.875F, 0.125F, 1.0F, 1.0F);
				} else if (facing == EnumFacing.WEST) {
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.125F, 1.0F, 0.125F);
				}
			}
		}
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
	
	// Redstone and interactions
	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos)
	{
		return this.getItem();
	}
	
	private Item getItem() {
		return ModItems.platform_gate_item;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return this.getItem();
	}
	
	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		BlockPos posToCheck = isUpper(world, pos) ? pos.down() : pos.up();
		world.setBlockToAir(pos);
		world.setBlockToAir(posToCheck);
		return true;
	}
}
