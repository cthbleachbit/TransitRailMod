package tk.cth451.transitrailmod.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tk.cth451.transitrailmod.TransitRailMod;
import tk.cth451.transitrailmod.tileentities.PlatformSignEntity;

public class PlatformSign extends BlockContainer {

	public PlatformSign(Material materialIn) {
		super(materialIn);
		setHardness(1.5F);
		setResistance(50);
		setLightLevel(0.7F);
		setHarvestLevel("pickaxe", 1);
		setUnlocalizedName("platform_sign");
		setCreativeTab(TransitRailMod.tabTransitRail);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new PlatformSignEntity("");
	}
}
