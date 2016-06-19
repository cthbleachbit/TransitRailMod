package tk.cth451.transitrailmod.blocks.prototype;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.enums.EnumArrow;
import tk.cth451.transitrailmod.init.ModItems;

public class ArrowSign extends Block {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum ARROW = PropertyEnum.create("arrow", EnumArrow.class);
	
	public ArrowSign(Material materialIn) {
		super(Material.iron);
		this.setLightLevel(1.0F);
		this.setDefaultState(getDefaultState()
				.withProperty(ARROW, EnumArrow.ARROW_UP)
				.withProperty(FACING, EnumFacing.NORTH));
	}
	
	// Block state
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {ARROW, FACING});
	}
	
	// meta 1122
	// 11: ARROW
	// 22: FACING
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing pFacing = EnumFacing.getHorizontal(meta % 4);
		EnumArrow pArrow = EnumArrow.fromMeta(meta / 4);
		return this.getDefaultState().withProperty(ARROW, pArrow).withProperty(FACING, pFacing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int mFacing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		int mArrow = ((EnumArrow) state.getValue(ARROW)).toMeta();
		return mArrow * 4 + mFacing;
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
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (playerIn.getHeldItem() != null) {
			if (playerIn.getHeldItem().getItem() == ModItems.style_changer){
				worldIn.setBlockState(pos, state.cycleProperty(ARROW));
				return true;
			}
		}
		return false;
	}
}
