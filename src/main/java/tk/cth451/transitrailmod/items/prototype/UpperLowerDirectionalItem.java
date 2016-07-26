package tk.cth451.transitrailmod.items.prototype;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class UpperLowerDirectionalItem extends Item {
	public final Block block;
	public final IProperty propertyUpper;
	public final IProperty propertyFacing;
	
	public UpperLowerDirectionalItem(Block blockIn, IProperty upperIn, IProperty facingIn) {
		super();
		this.block = blockIn;
		this.propertyUpper = upperIn;
		this.propertyFacing = facingIn;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (side != EnumFacing.UP) {
			return false;
		} else {
			if (!worldIn.getBlockState(pos).getBlock().isNormalCube()){
				return false;
			} else {
				IBlockState block1 = worldIn.getBlockState(pos.up());
				IBlockState block2 = worldIn.getBlockState(pos.up(2));
				boolean canPlace = block1.getBlock().equals(Blocks.air);
				canPlace = canPlace && block2.getBlock().equals(Blocks.air);
				// 2 blocks above must be air
				
				if (canPlace) {
					IBlockState state = this.block.getDefaultState().withProperty(propertyFacing, playerIn.getHorizontalFacing());
					worldIn.setBlockState(pos.up(), state.withProperty(propertyUpper, false));
					worldIn.setBlockState(pos.up(2), state.withProperty(propertyUpper, true));
					--stack.stackSize;
					return true;
				} else {
					return false;
				}
			}
		}
	}
}
