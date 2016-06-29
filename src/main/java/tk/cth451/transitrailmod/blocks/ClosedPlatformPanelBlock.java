package tk.cth451.transitrailmod.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.blocks.prototype.ClosedPlatformBlock;
import tk.cth451.transitrailmod.init.ModItems;

public class ClosedPlatformPanelBlock extends ClosedPlatformBlock {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public ClosedPlatformPanelBlock(Material materialIn) {
		super(Material.glass);
		this.setUnlocalizedName("closed_platform_panel_block");
		this.setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(UPPER, false));
	}
	
	// Properties
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
		EnumFacing facing = (EnumFacing) worldIn.getBlockState(pos).getValue(FACING);
		if (facing == EnumFacing.NORTH) {
			this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.25F);
		} else if (facing == EnumFacing.EAST) {
			this.setBlockBounds(0.75F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
		} else if (facing == EnumFacing.SOUTH) {
			this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 0.875F);
		} else if (facing == EnumFacing.WEST) {
			this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
		}
    }
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		boolean original = super.shouldSideBeRendered(worldIn, pos, side);
		boolean isSideConnected = (worldIn.getBlockState(pos.offset(side)).getBlock() != Blocks.air);
		return original && isSideConnected;
	}
	
	// Block State Methods
	@Override
	public BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING, UPPER});
	}
	

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(UPPER, isUpper(worldIn, pos));
	}
	
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}
	
	// Interactions
	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos)
	{
		return this.getItem();
	}
	
	private Item getItem() {
		return ModItems.closed_platform_panel_item;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return this.getItem();
	}
	
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		boolean flagUpper = isUpper(worldIn, pos);
		// check whether the block triggered is the upper part
		BlockPos pos1 = flagUpper ? pos.down() : pos.up();
		// get the other part
		Boolean flagOkay = (worldIn.getBlockState(pos1).getBlock() == this);
		// check whether the other part gets destroyed
		if (!flagOkay) {
			this.destroyParts(flagUpper, worldIn, pos);
		}
	}
}
