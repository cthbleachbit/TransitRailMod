package tk.cth451.transitrailmod.enums;
import net.minecraft.util.IStringSerializable;

public enum EnumArrow implements IStringSerializable {
		ARROW_UP,
		ARROW_DOWN,
		ARROW_LEFT,
		ARROW_RIGHT;
		
		public String toString() {
			return getName();
		};
		
		public int toMeta() {
			if (this == ARROW_UP) {
				return 0;
			} else if (this == ARROW_DOWN) {
				return 1;
			} else if (this == ARROW_LEFT) {
				return 2;
			} else {
				return 3;
			}
		}
		
		public static EnumArrow fromMeta(int metaIn) {
			EnumArrow result;
			if (metaIn == 0) {
				result = ARROW_UP;
			} else if (metaIn == 1) {
				result = ARROW_DOWN;
			} else if (metaIn == 2) {
				result = ARROW_LEFT;
			} else {
				result = ARROW_RIGHT;
			}
			return result;
		}
		
		@Override
		public String getName() {
			if (this == ARROW_UP) {
				return "arrow_up";
			} else if (this == ARROW_DOWN) {
				return "arrow_down";
			} else if (this == ARROW_LEFT) {
				return "arrow_left";
			} else {
				return "arrow_right";
			}
		}
	}