package tk.cth451.transitrailmod.items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (facing != EnumFacing.UP) {
			return EnumActionResult.PASS;
		} else {
			if (!worldIn.getBlockState(pos).getBlock().isNormalCube(worldIn.getBlockState(pos), worldIn, pos)){
				return EnumActionResult.PASS;
			} else {
				IBlockState block1 = worldIn.getBlockState(pos.up());
				IBlockState block2 = worldIn.getBlockState(pos.up(2));
				IBlockState block3 = worldIn.getBlockState(pos.up(3));
				boolean canPlace = block1.getBlock().equals(Blocks.AIR);
				canPlace = canPlace && block2.getBlock().equals(Blocks.AIR);
				canPlace = canPlace && block3.getBlock().equals(Blocks.AIR);
				// 3 blocks above must be air
				
				if (canPlace) {
					IBlockState panelState = panel.getDefaultState().withProperty(ClosedPlatformPanelBlock.FACING, playerIn.getHorizontalFacing());
					worldIn.setBlockState(pos.up(), panelState.withProperty(ClosedPlatformPanelBlock.UPPER, false));
					worldIn.setBlockState(pos.up(2), panelState.withProperty(ClosedPlatformPanelBlock.UPPER, true));
					IBlockState topState = top.getDefaultState().withProperty(ClosedPlatformTop.FACING, playerIn.getHorizontalFacing());
					worldIn.setBlockState(pos.up(3), topState);
					--stack.stackSize;
					return EnumActionResult.SUCCESS;
				} else {
					return EnumActionResult.PASS;
				}
			}
		}
	}
}
