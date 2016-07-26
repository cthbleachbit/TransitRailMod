package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
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
