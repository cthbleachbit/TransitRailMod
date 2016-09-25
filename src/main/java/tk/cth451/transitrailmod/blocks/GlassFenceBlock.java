package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.init.ModBlocks;

public class GlassFenceBlock extends BlockFence {
	
	public GlassFenceBlock() {
		super(Material.GLASS, MapColor.IRON);
		this.setUnlocalizedName("glass_fence");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
	}
	
	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		if (block == Blocks.GLASS || block == ModBlocks.turnstile_block) {
			return true;
		}
		return super.canConnectTo(worldIn, pos);
	}
}
