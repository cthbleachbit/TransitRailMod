package tk.cth451.transitrailmod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.init.ModBlocks;
import tk.cth451.transitrailmod.init.ModItems;

public class PlatformPanelBlock extends PlatformBlock {
	
	public PlatformPanelBlock(Material materialIn) {
		super(Material.iron);
		this.setUnlocalizedName("platform_panel_block");
		this.setDefaultState(getDefaultState().withProperty(UPPER, false).withProperty(FACING, EnumFacing.NORTH));
	}
	
	// Properties
	public boolean isUpper(IBlockAccess worldIn, BlockPos pos){
		return worldIn.getBlockState(pos.down()).getBlock().equals(this);
	}
	
	public boolean isUpper(IBlockState state){
		return (Boolean) state.getValue(UPPER);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
		EnumFacing facing = (EnumFacing) worldIn.getBlockState(pos).getValue(FACING);
		if (isUpper(worldIn, pos)) {
			if (facing == EnumFacing.NORTH) {
				this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 0.5F, 0.25F);
			} else if (facing == EnumFacing.EAST) {
				this.setBlockBounds(0.75F, 0.0F, 0.0F, 0.875F, 0.5F, 1.0F);
			} else if (facing == EnumFacing.SOUTH) {
				this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 0.5F, 0.875F);
			} else if (facing == EnumFacing.WEST) {
				this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.25F, 0.5F, 1.0F);
			}
		} else {
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
    }
	
	// Blockstates
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
	
	// Redstone and interactions
	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos)
	{
		return this.getItem();
	}
	
	private Item getItem() {
		return ModItems.platform_panel_item;
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
