package tk.cth451.transitrailmod.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.cth451.transitrailmod.tileentities.PlatformSignEntity;

public class ModTileEntities {
	public static void register() {
		GameRegistry.registerTileEntity(PlatformSignEntity.class, "platform_sign_entity");
	}
}
