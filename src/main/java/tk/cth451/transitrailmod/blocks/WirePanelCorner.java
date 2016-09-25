package tk.cth451.transitrailmod.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.enums.EnumCorner;
import tk.cth451.transitrailmod.init.ModBlocks;

public class WirePanelCorner extends Block {
	
	public static final PropertyEnum CORNER = PropertyEnum.create("corner", EnumCorner.class);
	public static final PropertyBool CONCAVE = PropertyBool.create("concave");
	
	public WirePanelCorner(Material materialIn) {
		super(Material.IRON);
		this.setUnlocalizedName("wire_panel_corner");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
		this.setDefaultState(this.getDefaultState()
				.withProperty(CORNER, EnumCorner.NE)
				.withProperty(CONCAVE, false));
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumCorner corner = (EnumCorner) state.getValue(CORNER);
		if ((Boolean) state.getValue(CONCAVE)) {
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else {
			if (corner == EnumCorner.SE) {
				return new AxisAlignedBB(0.5F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
			} else if (corner == EnumCorner.SW) {
				return new AxisAlignedBB(0.0F, 0.0F, 0.5F, 0.5F, 1.0F, 1.0F);
			} else if (corner == EnumCorner.NW) {
				return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 0.5F);
			} else { // NE
				return new AxisAlignedBB(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
			}
		}
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
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
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		state = state.withProperty(CONCAVE, this.getConcave(worldIn, pos));
		worldIn.setBlockState(pos, state);
	}
	
	// Block States
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {CORNER, CONCAVE});
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
}
