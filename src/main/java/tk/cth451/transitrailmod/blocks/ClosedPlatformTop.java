package tk.cth451.transitrailmod.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ClosedPlatformTop extends Block{
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	
	public ClosedPlatformTop(Material materialIn) {
		super(Material.iron);
		this.setLightLevel(1F);
		// Full brightness
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, 0));
	}
	
	@Override
	public BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING, POWERED});
	}
	
	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
    {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
    }
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	public boolean isSolidFullCube()
    {
        return false;
    }
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
		IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		return state.withProperty(FACING, facing.getHorizontalIndex());
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int keyFacing = meta/2;
		boolean keyPowered = (meta % 2 == 1) ? true : false;
		return this.getDefaultState().withProperty(FACING, keyFacing).withProperty(POWERED, keyPowered);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int keyFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int keyPowered = (Integer) state.getValue(POWERED);
		return keyFacing*2+keyPowered;
	}
}
