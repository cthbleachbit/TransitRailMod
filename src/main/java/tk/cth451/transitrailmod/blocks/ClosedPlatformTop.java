package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.blocks.prototype.CustomDirectionBlock;
import tk.cth451.transitrailmod.init.ModBlocks;
import tk.cth451.transitrailmod.init.ModItems;

public class ClosedPlatformTop extends CustomDirectionBlock {

	public static final PropertyBool POWERED = PropertyBool.create("powered");
	
	public ClosedPlatformTop(Material materialIn) {
		super(Material.IRON);
		this.setLightLevel(1F);
		// Full brightness
		this.setUnlocalizedName("closed_platform_top");
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(POWERED, false));
	}

	// Properties
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = (EnumFacing) source.getBlockState(pos).getValue(FACING);
		if (facing == EnumFacing.NORTH) {
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
		} else if (facing == EnumFacing.EAST) {
			return new AxisAlignedBB(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else if (facing == EnumFacing.SOUTH) {
			return new AxisAlignedBB(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
		} else { // if (facing == EnumFacing.WEST) {
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
		}
	}

	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
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
	public boolean canProvidePower(IBlockState state) {
		return true;
	}

	// Block state related
	// meta: 0211
	// 2: powered
	// 11 : facing
	@Override
	public int getMetaFromState(IBlockState state) {
		int mFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int mPowered = (Boolean) state.getValue(POWERED) ? 1 : 0;
		return mPowered * 4 + mFacing;
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, POWERED });
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
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		// TODO: A dirty hack here.
		boolean isCreative = player.capabilities.isCreativeMode;

		if (this.getBlockBelow(world, pos).getUnlocalizedName().equals("tile.closed_platform_door_block")
				|| this.getBlockBelow(world, pos).getUnlocalizedName().equals("tile.closed_platform_panel_block")) {
			world.destroyBlock(pos.down(), !isCreative);
			world.setBlockToAir(pos.down(2));
			world.setBlockToAir(pos);
		} else {
			world.destroyBlock(pos, false);
		}
		return true;
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return false;
	}
	
	// Redstone
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		Boolean flag = worldIn.isBlockPowered(pos);
		if (flag != (Boolean) state.getValue(POWERED)) {
			worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(flag)), 2);
			worldIn.markBlockRangeForRenderUpdate(pos, pos);
			worldIn.notifyBlockOfStateChange(pos.down(), this);
		}
	}

	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.DOWN) {
			return (Boolean) blockState.getValue(POWERED) ? 15 : 0;
		} else {
			return 0;
		}
	}

	// When middle click, get the block below.
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		Item item = null;
		Block blockBelow = this.getBlockBelow(world, pos);

		if (blockBelow == ModBlocks.closed_platform_door_block) {
			item = ModItems.closed_platform_door_item;
		} else if (blockBelow == ModBlocks.closed_platform_panel_block) {
			item = ModItems.closed_platform_panel_item;
		}

		if (item == null)
			return null;

		return new ItemStack(item, 1);
	}
	
	private Block getBlockBelow(IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).getBlock();
	}
}
