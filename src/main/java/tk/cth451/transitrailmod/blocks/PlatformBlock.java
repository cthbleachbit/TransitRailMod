package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class PlatformBlock extends Block {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool UPPER = PropertyBool.create("upper");
	
	public PlatformBlock(Material materialIn) {
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
	
	// Interactions
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
		// set facing to the direction player is facing
		IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		EnumFacing thisFacing = placer.getHorizontalFacing();
		return this.getActualState(state, worldIn, pos).withProperty(FACING, thisFacing);
	}
}