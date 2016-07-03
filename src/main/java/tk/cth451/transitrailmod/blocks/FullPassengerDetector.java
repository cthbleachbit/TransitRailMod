package tk.cth451.transitrailmod.blocks;

import net.minecraft.block.material.Material;
import tk.cth451.transitrailmod.blocks.prototype.EntityDetector;

public class FullPassengerDetector extends EntityDetector{
	
	public FullPassengerDetector(Material materialIn) {
		super(Material.iron);
		this.setUnlocalizedName("full_passenger_detector");
	}
}
