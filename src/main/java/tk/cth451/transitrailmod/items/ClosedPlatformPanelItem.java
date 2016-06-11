package tk.cth451.transitrailmod.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.ClosedPlatformPanelBlock;
import tk.cth451.transitrailmod.blocks.ClosedPlatformTop;
import tk.cth451.transitrailmod.init.ModBlocks;

public class ClosedPlatformPanelItem extends Item {
	
	private Block panel = ModBlocks.closed_platform_panel_block;
	private Block top = ModBlocks.closed_platform_top;
	
	public ClosedPlatformPanelItem() {
		super();
		setUnlocalizedName("closed_platform_panel_item");
		setCreativeTab(TransitRailMod.tabTransitRail);
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
				IBlockState block3 = worldIn.getBlockState(pos.up(3));
				boolean canPlace = block1.getBlock().equals(Blocks.air);
				canPlace = canPlace && block2.getBlock().equals(Blocks.air);
				canPlace = canPlace && block3.getBlock().equals(Blocks.air);
				// 3 blocks above must be air
				
				if (canPlace) {
					IBlockState panelState = panel.getDefaultState().withProperty(ClosedPlatformPanelBlock.FACING, playerIn.getHorizontalFacing());
					worldIn.setBlockState(pos.up(), panelState.withProperty(ClosedPlatformPanelBlock.UPPER, false));
					worldIn.setBlockState(pos.up(2), panelState.withProperty(ClosedPlatformPanelBlock.UPPER, true));
					IBlockState topState = top.getDefaultState().withProperty(ClosedPlatformTop.FACING, playerIn.getHorizontalFacing());
					worldIn.setBlockState(pos.up(3), topState);
					--stack.stackSize;
					return true;
				} else {
					return false;
				}
			}
		}
	}
}
