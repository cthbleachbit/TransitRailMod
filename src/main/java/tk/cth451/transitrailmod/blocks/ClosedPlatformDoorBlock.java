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
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.init.ModBlocks;
import tk.cth451.transitrailmod.init.ModItems;

public class ClosedPlatformDoorBlock extends ClosedPlatformBlock {
	
	public static final PropertyBool UPPER = PropertyBool.create("upper");
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	public static final PropertyBool LEFT = PropertyBool.create("left"); 
	
	public ClosedPlatformDoorBlock(Material materialIn) {
		super(Material.glass);
		this.setUnlocalizedName("closed_platform_door_block");
		this.setDefaultState(getDefaultState()
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(UPPER, false)
				.withProperty(POWERED, false)
				.withProperty(LEFT, false));
	}
	
	// Properties
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
		EnumFacing facing = (EnumFacing) worldIn.getBlockState(pos).getValue(FACING);
		boolean isOpen = (Boolean) worldIn.getBlockState(pos).getValue(POWERED);
		boolean leftOrNot = this.isLeft(worldIn, pos, facing);
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
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list,
			Entity collidingEntity) {
		this.setBlockBoundsBasedOnState(worldIn, pos);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
		
	// BlockStates
	@Override
	public BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING, UPPER, POWERED, LEFT});
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing direc = (EnumFacing) state.getValue(FACING);
		return state.withProperty(UPPER, isUpper(worldIn, pos)).withProperty(LEFT, isLeft(worldIn, pos, direc)).withProperty(POWERED, this.shouldBePowered((World) worldIn, pos, state));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}
	
	public boolean isUpper(IBlockAccess worldIn, BlockPos pos){
		return worldIn.getBlockState(pos.down()).getBlock().equals(this);
	}
	
	public boolean isLeft(IBlockAccess worldIn, BlockPos pos, EnumFacing direc){
		return worldIn.getBlockState(pos.offset(direc.rotateY())).getBlock().equals(this);
	}
	
	// Interactions
	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		if (isUpper(world, pos)) {
			world.setBlockToAir(pos.up());
			world.setBlockToAir(pos.down());
		} else {
			world.setBlockToAir(pos.up());
			world.setBlockToAir(pos.up(2));
		}
		world.setBlockToAir(pos);
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos)
	{
		return this.getItem();
	}
	
	private Item getItem() {
		return ModItems.closed_platform_door_item;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return this.getItem();
	}
	
	// Redstone
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		if (neighborBlock.equals(ModBlocks.closed_platform_top)) {
			System.out.println(shouldBePowered(worldIn, pos, state));
			Minecraft.getMinecraft().renderGlobal.markBlockForUpdate(pos);
			super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
		}
	}
	
	public boolean shouldBePowered (World worldIn, BlockPos pos, IBlockState state) {
		BlockPos pos1 = pos.up((Boolean) state.getValue(UPPER) ? 1 : 2);
		EnumFacing direc = (EnumFacing) state.getValue(FACING);
		return worldIn.isBlockPowered(pos1.up()) || worldIn.isBlockPowered(pos1.offset(direc));
	}
	
}
