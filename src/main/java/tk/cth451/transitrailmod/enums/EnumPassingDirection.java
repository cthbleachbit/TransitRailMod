package tk.cth451.transitrailmod.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumPassingDirection implements IStringSerializable {
	INSIDE,
	OUTSIDE;
	
	public int toMeta() {
		return this == INSIDE ? 0 : 1;
	}
	
	public static EnumPassingDirection fromMeta(int meta) {
		return meta == 0 ? INSIDE : OUTSIDE;
	}
	
	@Override
	public String getName() {
		return this == INSIDE ? "inside" : "outside";
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
