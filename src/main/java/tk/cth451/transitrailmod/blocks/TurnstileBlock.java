package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.blocks.prototype.CustomDirectionBlock;
import tk.cth451.transitrailmod.enums.EnumPassingDirection;

public class TurnstileBlock extends CustomDirectionBlock{
	
	public static final PropertyBool ACTIVE = PropertyBool.create("active");
	// whether the turnstile has processed the ticket and open the gate
	public static final PropertyEnum PASSING = PropertyEnum.create("passing", EnumPassingDirection.class);
	
	public TurnstileBlock(Material materialIn) {
		super(Material.iron);
		this.setUnlocalizedName("turnstile_block");
		this.setDefaultState(this.getDefaultState()
				.withProperty(ACTIVE, false)
				.withProperty(PASSING, EnumPassingDirection.INSIDE)
				.withProperty(FACING, EnumFacing.NORTH));
	}
	
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
	
	// Block State
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {ACTIVE, PASSING, FACING});
	}
	
	// meta: 3211
	// 11: facing
	// 2: active
	// 3: passing
	@Override
	public int getMetaFromState(IBlockState state) {
		int mFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int mActive = (Boolean) state.getValue(ACTIVE) ? 1 : 0;
		int mPassing = ((EnumPassingDirection) state.getValue(PASSING)).toMeta();
		return mPassing * 8 + mActive * 4 + mFacing;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing pFacing = EnumFacing.getHorizontal(meta % 4);
		boolean pActive = (meta % 8) / 4 > 0;
		EnumPassingDirection pPassing = EnumPassingDirection.fromMeta(meta / 8);
		return getDefaultState()
				.withProperty(ACTIVE, pActive)
				.withProperty(PASSING, pPassing)
				.withProperty(FACING, pFacing);
	}
	
	// Appearance
	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.SOLID;
	}
}
