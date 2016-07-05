package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.TransitRailMod;

public class TicketMachine extends Block {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public TicketMachine(Material materialIn) {
		super(Material.iron);
		this.setUnlocalizedName("ticket_machine");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
		this.setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING});
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
		// set facing to the direction player is facing
		IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		return this.getFacingState(state, placer);
	}
	
	protected IBlockState getFacingState(IBlockState state, EntityLivingBase placer){
		EnumFacing thisFacing = placer.getHorizontalFacing();
		return state.withProperty(FACING, thisFacing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}
}
