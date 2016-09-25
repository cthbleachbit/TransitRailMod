package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.prototype.CustomDirectionBlock;
import tk.cth451.transitrailmod.enums.EnumAttachTo;
import tk.cth451.transitrailmod.init.ModBlocks;

public class FluorescentLamp extends CustomDirectionBlock {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum ATTACH = PropertyEnum.create("attach", EnumAttachTo.class);
	
	public FluorescentLamp(Material materialIn) {
		super(Material.GLASS);
		this.setUnlocalizedName("fluorescent_lamp");
		this.setLightLevel(1F);
		this.setCreativeTab(TransitRailMod.tabTransitRail);
		this.setDefaultState(getDefaultState()
				.withProperty(ATTACH, EnumAttachTo.WALL)
				.withProperty(FACING, EnumFacing.NORTH));
	}
	
	// Properties
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
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	// Block States
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {ATTACH, FACING});
	}
	
	// meta 1122
	// 11: ATTACH
	// 22: FACING
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing pFacing = EnumFacing.getHorizontal(meta % 4);
		EnumAttachTo pAttach = EnumAttachTo.fromMeta(meta / 4);
		return this.getDefaultState().withProperty(ATTACH, pAttach).withProperty(FACING, pFacing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int mFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int mAttach = ((EnumAttachTo) state.getValue(ATTACH)).toMeta();
		return mAttach * 4 + mFacing;
	}
	
	public IBlockState getAttached (World worldIn, BlockPos pos, IBlockState state){
		boolean pAbove = worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.fluorescent_lamp;
		boolean pPanel;
		if (worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.wire_panel) {
			pPanel = !((Boolean) worldIn.getBlockState(pos.down()).getValue(WirePanel.SHUT));
		} else {
			pPanel = worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.glass_fence;
		}
		
		if (pPanel || pAbove) {
			state = state.withProperty(ATTACH, EnumAttachTo.EXTENDING);
		} else {
			EnumFacing thisFacing = (EnumFacing) state.getValue(FACING);
			boolean pWall = worldIn.getBlockState(pos.offset(thisFacing)).getBlock().isSideSolid(state, worldIn, pos.offset(thisFacing), thisFacing.getOpposite());
			boolean pGround = worldIn.getBlockState(pos.down()).getBlock().isSideSolid(state, worldIn, pos.down(), EnumFacing.UP);
			boolean pCeiling = worldIn.getBlockState(pos.up()).getBlock().isSideSolid(state, worldIn, pos.up(), EnumFacing.DOWN);
			
			if (pWall) {
				state = state.withProperty(ATTACH, EnumAttachTo.WALL);
			} else if (pGround) {
				state = state.withProperty(ATTACH, EnumAttachTo.GROUND);
			} else if (pCeiling) {
				state = state.withProperty(ATTACH, EnumAttachTo.CEILING);
			} else {
				state = state.withProperty(ATTACH, EnumAttachTo.WALL);
			}
		}
		return state;
	}
	
	// Interactions
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		// set facing to the direction player is facing
		IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		state = this.getAttached(worldIn, pos, state);
		return this.getFacingState(state, placer);
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		worldIn.setBlockState(pos, this.getAttached(worldIn, pos, state));
	}
	
	// Appearance
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if ((EnumAttachTo) state.getValue(ATTACH) == EnumAttachTo.GROUND) {
			return new AxisAlignedBB(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
		} else if ((EnumAttachTo) state.getValue(ATTACH) == EnumAttachTo.EXTENDING){
			return new AxisAlignedBB(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
		} else if ((EnumAttachTo) state.getValue(ATTACH) == EnumAttachTo.CEILING) {
			switch ((EnumFacing) state.getValue(FACING)) {
				case NORTH:
					return new AxisAlignedBB(0.0F, 0.875F, 0.375F, 1.0F, 1.0F, 0.625F);
				case SOUTH:
					return new AxisAlignedBB(0.0F, 0.875F, 0.375F, 1.0F, 1.0F, 0.625F);
				default: // EAST and WEST
					return new AxisAlignedBB(0.375F, 0.875F, 0.0F, 0.625F, 1.0F, 1.0F);
			}
		} else { // if ((EnumAttachTo) state.getValue(ATTACH) == EnumAttachTo.WALL) {
			switch ((EnumFacing) state.getValue(FACING)) {
			case NORTH:
				return new AxisAlignedBB(0.0F, 0.4375F, 0.0F, 1.0F, 0.625F, 0.125F);
			case EAST:
				return new AxisAlignedBB(0.875F, 0.4375F, 0.0F, 1.0F, 0.625F, 1.0F);
			case SOUTH:
				return new AxisAlignedBB(0.0F, 0.4375F, 0.875F, 1.0F, 0.625F, 1.0F);
			default: // WEST
				return new AxisAlignedBB(0.0F, 0.4375F, 0.0F, 0.125F, 0.625F, 1.0F);
			}
		}
	}
}
