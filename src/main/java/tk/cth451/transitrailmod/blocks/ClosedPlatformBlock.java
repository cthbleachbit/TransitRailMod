package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClosedPlatformBlock extends Block {

	public ClosedPlatformBlock(Material materialIn) {
		super(materialIn);
	}
	
	// Common Properties
	@Override
	public boolean isTranslucent() {
		return true;
	}
	
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
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
}
