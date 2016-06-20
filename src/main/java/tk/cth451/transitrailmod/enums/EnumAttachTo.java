package tk.cth451.transitrailmod.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumAttachTo implements IStringSerializable {
	WALL,
	GROUND,
	CEILING;
	
	public int toMeta() {
		if (this == WALL) {
			return 0;
		} else if (this == GROUND) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public static EnumAttachTo fromMeta(int metaIn) {
		EnumAttachTo result;
		if (metaIn == 0) {
			result = WALL;
		} else if (metaIn == 1) {
			result = GROUND;
		} else {
			result = CEILING;
		}
		return result;
	}
	
	@Override
	public String getName() {
		if (this == WALL) {
			return "wall";
		} else if (this == GROUND) {
			return "ground";
		} else {
			return "ceiling";
		}
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
