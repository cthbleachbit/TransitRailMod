package tk.cth451.transitrailmod.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.enums.EnumCorner;
import tk.cth451.transitrailmod.init.ModBlocks;

public class WirePanelCorner extends Block {
	
	public static final PropertyEnum CORNER = PropertyEnum.create("corner", EnumCorner.class);
	public static final PropertyBool CONCAVE = PropertyBool.create("concave");
	
	public WirePanelCorner(Material materialIn) {
		super(Material.iron);
		this.setUnlocalizedName("wire_panel_corner");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
		this.setDefaultState(this.getDefaultState()
				.withProperty(CORNER, EnumCorner.NE)
				.withProperty(CONCAVE, false));
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		EnumCorner corner = (EnumCorner) state.getValue(CORNER);
		if ((Boolean) state.getValue(CONCAVE)) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else {
			if (corner == EnumCorner.SE) {
				this.setBlockBounds(0.5F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
			} else if (corner == EnumCorner.SW) {
				this.setBlockBounds(0.0F, 0.0F, 0.5F, 0.5F, 1.0F, 1.0F);
			} else if (corner == EnumCorner.NW) {
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 0.5F);
			} else { // NE
				this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
			}
		}
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean isFullCube()
    {
        return false;
    }
	
	// Interactions
	protected EnumCorner getCorner(EntityLivingBase playerIn){
		double xIndex = playerIn.getLookVec().xCoord;
		double zIndex = playerIn.getLookVec().zCoord;
		int index;
		if (xIndex > 0){
			index = zIndex > 0 ? 0 : 3;
			// x+ and z+ = south east
			// x+ and z- = north east
		} else {
			index = zIndex > 0 ? 1 : 2;
			// x- and z+ = south west
			// x- and z- = north west
		}
		return EnumCorner.fromMeta(index);
	}
	
	protected boolean getConcave(IBlockAccess worldIn, BlockPos pos) {
		return this.getConcave(worldIn, pos, worldIn.getBlockState(pos));
	}
	
	// to specify state before the block is established
	protected boolean getConcave(IBlockAccess worldIn, BlockPos pos, IBlockState state) {
		EnumCorner corner = (EnumCorner) state.getValue(CORNER);
		for (EnumFacing facing : corner.getConcaveConnections()){
			Block block = worldIn.getBlockState(pos.offset(facing)).getBlock();
			if (block == ModBlocks.wire_panel || block == ModBlocks.wire_panel_corner) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		state = state.withProperty(CORNER, this.getCorner(placer));
		return state.withProperty(CONCAVE, this.getConcave(worldIn, pos, state));
	}
	
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		state = state.withProperty(CONCAVE, this.getConcave(worldIn, pos));
		worldIn.setBlockState(pos, state);
	}
	
	// Block States
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {CORNER, CONCAVE});
	}
	
	// meta: 0211
	// 2: concave
	// 11: corner
	@Override
	public int getMetaFromState(IBlockState state) {
		int mCorner = ((EnumCorner) state.getValue(CORNER)).toMeta();
		int mConcave = (Boolean) state.getValue(CONCAVE) ? 1 : 0;
		return mConcave * 4 + mCorner;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumCorner pCorner = EnumCorner.fromMeta(meta % 4);
		boolean pConcave = meta / 4 > 0;
		return this.getDefaultState()
				.withProperty(CONCAVE, pConcave)
				.withProperty(CORNER, pCorner);
	}
	
	// Appearance
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list,
			Entity collidingEntity) {
		this.setBlockBoundsBasedOnState(worldIn, pos);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}
}
