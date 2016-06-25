package tk.cth451.transitrailmod.blocks.prototype;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ClosedPlatformBlock extends CustomDirectionBlock {
	
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
		return EnumWorldBlockLayer.TRANSLUCENT;
	}
	
	// Interactions
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		worldIn.notifyBlockOfStateChange(pos.down(), this);
	}
	
	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
}
