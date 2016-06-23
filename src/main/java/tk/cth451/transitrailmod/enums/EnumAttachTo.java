package tk.cth451.transitrailmod.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumAttachTo implements IStringSerializable {
	WALL,
	GROUND,
	CEILING,
	PANEL;
	
	public int toMeta() {
		if (this == WALL) {
			return 0;
		} else if (this == GROUND) {
			return 1;
		} else if (this == CEILING){
			return 2;
		} else { // PANEL
			return 3;
		}
	}
	
	public static EnumAttachTo fromMeta(int metaIn) {
		EnumAttachTo result;
		if (metaIn == 0) {
			result = WALL;
		} else if (metaIn == 1) {
			result = GROUND;
		} else if (metaIn == 2) {
			result = CEILING;
		} else {
			result = PANEL;
		}
		return result;
	}
	
	@Override
	public String getName() {
		if (this == WALL) {
			return "wall";
		} else if (this == GROUND) {
			return "ground";
		} else if (this == CEILING) {
			return "ceiling";
		} else {
			return "PANEL";
		}
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
