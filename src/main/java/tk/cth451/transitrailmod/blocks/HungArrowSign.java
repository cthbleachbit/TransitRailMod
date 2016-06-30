package tk.cth451.transitrailmod.blocks;

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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.blocks.prototype.ArrowSign;
import tk.cth451.transitrailmod.enums.EnumArrow;
import tk.cth451.transitrailmod.init.ModItems;

public class HungArrowSign extends ArrowSign {
		
	public HungArrowSign(Material materialIn) {
		super(Material.iron);
		this.setUnlocalizedName("hung_arrow_sign");
		this.setCreativeTab(TransitRailMod.tabTransitRail);
	}
	
	// Properties
	@Override
	public boolean isFullCube()
    {
        return false;
    }
	
	// Appearance
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		EnumFacing facing = (EnumFacing) worldIn.getBlockState(pos).getValue(FACING);
		if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
			this.setBlockBounds(0.0F, 0.5F, 0.4375F, 1.0F, 1.0F, 0.5625F);
		} else {
			this.setBlockBounds(0.4375F, 0.5F, 0.0F, 0.5625F, 1.0F, 1.0F);
		}
	}
}
