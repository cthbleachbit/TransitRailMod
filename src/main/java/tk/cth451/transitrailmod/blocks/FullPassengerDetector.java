package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import tk.cth451.transitrailmod.blocks.prototype.EntityDetector;

public class FullPassengerDetector extends EntityDetector {
	public FullPassengerDetector(Material materialIn) {
		super(Material.iron);
		this.setUnlocalizedName("full_passenger_detector");
	}
	
	// Common Properties
	@Override
	public boolean isTranslucent() {
		return true;
	}
	
	@Override
	public int getMobilityFlag()
    {
        return 1;
    }
	
	@Override
	public boolean isOpaqueCube() {
        return false;
    }
	
	@Override
	public boolean isFullCube()
    {
        return false;
    }
}
