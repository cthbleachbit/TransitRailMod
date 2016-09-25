package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.prototype.CustomDirectionBlock;
import tk.cth451.transitrailmod.init.ModBlocks;
import tk.cth451.transitrailmod.init.ModItems;

public class WirePanel extends CustomDirectionBlock {
	
	public static final PropertyBool LAMP = PropertyBool.create("lamp");
	public static final PropertyBool SHUT = PropertyBool.create("shut");
	
	public WirePanel(Material materialIn) {
		super(Material.IRON);
		this.setUnlocalizedName("wire_panel");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
		this.setDefaultState(this.getDefaultState()
				.withProperty(LAMP, false)
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(SHUT, false));
	}
	
	// Properties
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = (EnumFacing) state.getValue(FACING); 
		if (facing == EnumFacing.NORTH) {
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
		} else if (facing == EnumFacing.EAST) {
			return new AxisAlignedBB(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else if (facing == EnumFacing.SOUTH) {
			return new AxisAlignedBB(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
		} else { // WEST
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
		}
	}
	
	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
	
	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}
	
	// Block States
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING, LAMP, SHUT});
	}
	
	// meta: 3211
	// 11: facing
	// 2: lamp
	// 3: shut
	@Override
	public int getMetaFromState(IBlockState state) {
		int mFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int mLamp = (Boolean) state.getValue(LAMP) ? 1 : 0;
		int mShut = (Boolean) state.getValue(SHUT) ? 1 : 0;
		return mShut * 8 + mLamp * 4 + mFacing;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing pFacing = EnumFacing.getHorizontal(meta % 4);
		boolean pLamp = ((meta % 8) / 4) > 0;
		boolean pShut = meta / 8 > 0;
		return this.getDefaultState()
				.withProperty(FACING, pFacing)
				.withProperty(LAMP, pLamp)
				.withProperty(SHUT, pShut);
	}
	
	// Interactions
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
		// set facing to the direction player is facing
		IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		return this.getFacingState(state, placer)
				.withProperty(LAMP, this.checkLampPresent(worldIn, pos))
				.withProperty(SHUT, this.checkIsExtendingAbove(worldIn,pos));
	}
	
	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		IBlockState state = world.getBlockState(pos);
		EnumFacing sideToProvide = ((EnumFacing) state.getValue(FACING)).getOpposite();
		state = state.withProperty(LAMP, this.checkLampPresent(world, pos));
		((World) world).setBlockState(pos, state);
		((World) world).notifyBlockOfStateChange(pos.offset(sideToProvide), this);
	}
	
	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		if (this.checkLampPresent(blockAccess, pos) && !((Boolean) blockState.getValue(SHUT))) {
			EnumFacing sideToProvide = ((EnumFacing) blockState.getValue(FACING));
			return sideToProvide == side ? 15 : 0;
		} else {
			return 0;
		}
	}
	
	protected boolean checkLampPresent(IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.up()).getBlock() == ModBlocks.fluorescent_lamp ? true : worldIn.getBlockState(pos.up()).getBlock() == ModBlocks.noise_barrier_with_lamp;
	}
	
	protected boolean checkIsExtendingAbove(IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.wire_panel;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (playerIn.getHeldItemMainhand() != null) {
			if (playerIn.getHeldItemMainhand().getItem() == ModItems.style_changer){
				worldIn.setBlockState(pos, state.cycleProperty(SHUT));
				return true;
			}
		}
		return false;
	}
}
