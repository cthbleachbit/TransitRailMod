package tk.cth451.transitrailmod.blocks.prototype;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
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
	public boolean isTranslucent() {
		return true;
	}
	
	@Override
	public int getMobilityFlag()
    {
        return 1;
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
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		boolean original = super.shouldSideBeRendered(worldIn, pos, side);
		boolean isSideConnected = (worldIn.getBlockState(pos.offset(side)).getBlock() != Blocks.air);
		return original && isSideConnected;
	}
	
	protected boolean isUpper(IBlockAccess worldIn, BlockPos pos){
		return worldIn.getBlockState(pos.up()).getBlock() == ModBlocks.closed_platform_top;
	}
	
	// Interactions
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		worldIn.notifyBlockOfStateChange(pos.down(), this);
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
