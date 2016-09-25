package tk.cth451.transitrailmod.blocks;

import java.util.List;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NoiseBarrierBlock extends BlockFence {
	
	public final boolean lamp;
	
	public NoiseBarrierBlock(boolean lampIn) {
		super(Material.GLASS, MapColor.IRON);
		lamp = lampIn;
		this.setUnlocalizedName(lamp ? "noise_barrier_with_lamp" : "noise_barrier");
		this.setLightLevel(lamp ? 1.0F : 0F);
		// TODO Auto-generated constructor stub
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
	
	// Bounding boxes
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		boolean flag = this.canConnectTo(worldIn, pos.north());
		boolean flag1 = this.canConnectTo(worldIn, pos.south());
		boolean flag2 = this.canConnectTo(worldIn, pos.west());
		boolean flag3 = this.canConnectTo(worldIn, pos.east());
		float f = 0.4375F;
		float f1 = 0.5625F;
		float f2 = 0.4375F;
		float f3 = 0.5625F;

		if (flag) {
			f2 = 0.0F;
		}
		
		if (flag1) {
			f3 = 1.0F;
		}
		
		if (flag || flag1) {
			return new AxisAlignedBB(f, 0.0F, f2, f1, 1.0F, f3);
		}

		f2 = 0.4375F;
		f3 = 0.5625F;

		if (flag2) {
			f = 0.0F;
		}

		if (flag3) {
			f1 = 1.0F;
		}

		if (flag2 || flag3 || !flag && !flag1) {
			return new AxisAlignedBB(f, 0.0F, f2, f1, 1.0F, f3);
		}

		if (flag) {
			f2 = 0.0F;
		}

		if (flag1) {
			f3 = 1.0F;
		}

		return new AxisAlignedBB(f, 0.0F, f2, f1, 1.0F, f3);
	}
}
