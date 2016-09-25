package tk.cth451.transitrailmod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
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

public class ClosedPlatformPanelBlock extends ClosedPlatformBlock {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public ClosedPlatformPanelBlock(Material materialIn) {
		super(Material.GLASS);
		this.setUnlocalizedName("closed_platform_panel_block");
		this.setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(UPPER, false));
	}
	
	// Properties
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = (EnumFacing) source.getBlockState(pos).getValue(FACING);
		if (facing == EnumFacing.NORTH) {
			return new AxisAlignedBB(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.25F);
		} else if (facing == EnumFacing.EAST) {
			return new AxisAlignedBB(0.75F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
		} else if (facing == EnumFacing.SOUTH) {
			return new AxisAlignedBB(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 0.875F);
		} else { // if (facing == EnumFacing.WEST) {
			return new AxisAlignedBB(0.125F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
		}
	}
	
	// Block State Methods
	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING, UPPER});
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
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
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
