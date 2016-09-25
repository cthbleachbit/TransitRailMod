package tk.cth451.transitrailmod.blocks.prototype;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.init.ModBlocks;

public abstract class ClosedPlatformBlock extends CustomDirectionBlock {
	
	public static final PropertyBool UPPER = PropertyBool.create("upper");
	
	public ClosedPlatformBlock(Material materialIn) {
		super(materialIn);
	}
	
	// Common Properties
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
	
	protected boolean isUpper(IBlockAccess worldIn, BlockPos pos){
		return worldIn.getBlockState(pos.up()).getBlock() == ModBlocks.closed_platform_top;
	}
	
	// Interactions
	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		((World) world).notifyBlockOfStateChange(pos.down(), this);
	}
	
	protected void destroyParts(Boolean flagUpper, World worldIn, BlockPos thisPos){
		BlockPos pos2 = flagUpper ? thisPos.up() : thisPos.up(2);
		// pos2 = the top part
		worldIn.setBlockToAir(thisPos);
		// destroy self
		worldIn.setBlockToAir(pos2);
		// destroy top
	}
	
	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
}
