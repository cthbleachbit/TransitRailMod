package tk.cth451.transitrailmod.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.enums.EnumArrow;
import tk.cth451.transitrailmod.enums.EnumAttachTo;
import tk.cth451.transitrailmod.init.ModBlocks;

public class FluorescentLamp extends Block {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum ATTACH = PropertyEnum.create("attach", EnumAttachTo.class);
	
	public FluorescentLamp(Material materialIn) {
		super(Material.glass);
		this.setUnlocalizedName("fluorescent_lamp");
		this.setLightLevel(0.95F);
		this.setCreativeTab(TransitRailMod.tabTransitRail);
		this.setDefaultState(getDefaultState()
				.withProperty(ATTACH, EnumAttachTo.WALL)
				.withProperty(FACING, EnumFacing.NORTH));
	}
	
	// Properties
	@Override
	public boolean isTranslucent() {
		return true;
	}
	
	@Override
	public boolean isOpaqueCube() {
        return false;
    }
	
	@Override
	public boolean isFullCube()
    {
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}
	
	// Block States
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {ATTACH, FACING});
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
		boolean pPanel = worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.wire_panel;
		boolean pAbove = worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.fluorescent_lamp;
		if (pPanel || pAbove) {
			state = state.withProperty(ATTACH, EnumAttachTo.EXTENDING);
		} else{
			EnumFacing thisFacing = (EnumFacing) state.getValue(FACING);
			boolean pWall = worldIn.getBlockState(pos.offset(thisFacing)).getBlock().isSideSolid(worldIn, pos.offset(thisFacing), thisFacing.getOpposite());
			boolean pGround = worldIn.getBlockState(pos.down()).getBlock().isSideSolid(worldIn, pos.down(), EnumFacing.UP);
			boolean pCeiling = worldIn.getBlockState(pos.up()).getBlock().isSideSolid(worldIn, pos.up(), EnumFacing.DOWN);
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
		EnumFacing thisFacing = placer.getHorizontalFacing();
		return state.withProperty(FACING, thisFacing);
	}
	
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		worldIn.setBlockState(pos, this.getAttached(worldIn, pos, state));
	}
	
	// Appearance
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		if ((EnumAttachTo) state.getValue(ATTACH) == EnumAttachTo.GROUND) {
			this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
		} else if ((EnumAttachTo) state.getValue(ATTACH) == EnumAttachTo.EXTENDING){
			this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
		} else if ((EnumAttachTo) state.getValue(ATTACH) == EnumAttachTo.CEILING) {
			switch ((EnumFacing) state.getValue(FACING)) {
				case NORTH:
					this.setBlockBounds(0.0F, 0.875F, 0.375F, 1.0F, 1.0F, 0.625F);
					break;
				case SOUTH:
					this.setBlockBounds(0.0F, 0.875F, 0.375F, 1.0F, 1.0F, 0.625F);
					break;
				default: // EAST and WEST
					this.setBlockBounds(0.375F, 0.875F, 0.0F, 0.625F, 1.0F, 1.0F);
					break;
			}
		} else if ((EnumAttachTo) state.getValue(ATTACH) == EnumAttachTo.WALL) {
			switch ((EnumFacing) state.getValue(FACING)) {
			case NORTH:
				this.setBlockBounds(0.0F, 0.4375F, 0.0F, 1.0F, 0.625F, 0.125F);
				break;
			case EAST:
				this.setBlockBounds(0.875F, 0.4375F, 0.0F, 1.0F, 0.625F, 1.0F);
				break;
			case SOUTH:
				this.setBlockBounds(0.0F, 0.4375F, 0.875F, 1.0F, 0.625F, 1.0F);
				break;
			default: // WEST
				this.setBlockBounds(0.0F, 0.4375F, 0.0F, 0.125F, 0.625F, 1.0F);
				break;
			}
		}
	}

	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list,
			Entity collidingEntity) {
		this.setBlockBoundsBasedOnState(worldIn, pos);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
}
