package tk.cth451.transitrailmod.enums;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

public enum EnumCorner implements IStringSerializable{
	SE,
	SW,
	NW,
	NE;
	
	public String toString() {
		return getName();
	};
	
	public int toMeta() {
		if (this == SE) {
			return 0;
		} else if (this == SW) {
			return 1;
		} else if (this == NW){
			return 2;
		} else { // NE
			return 3;
		}
	}
	
	public static EnumCorner fromMeta(int metaIn) {
		EnumCorner result;
		if (metaIn == 0) {
			result = SE;
		} else if (metaIn == 1) {
			result = SW;
		} else if (metaIn == 2) {
			result = NW;
		} else {
			result = NE;
		}
		return result;
	}
	
	@Override
	public String getName() {
		if (this == SE) {
			return "se";
		} else if (this == SW) {
			return "sw";
		} else if (this == NW){
			return "nw";
		} else { // NE
			return "ne";
		}
	}
	
	// rotate about y axis clockwise
	public EnumCorner rotateYCW(){
		int target = (this.toMeta() + 1) & 3;
		return fromMeta(target);
	}
	
	// rotate about y axis counter clockwise
	public EnumCorner rotateYCCW(){
		int target = (this.toMeta() + 3) & 3;
		return fromMeta(target);
	}
	
	public EnumFacing[] getConcaveConnections() {
		if (this == SE) {
			return new EnumFacing[] {EnumFacing.NORTH, EnumFacing.WEST};
		} else if (this == SW) {
			return new EnumFacing[] {EnumFacing.NORTH, EnumFacing.EAST};
		} else if (this == NW){
			return new EnumFacing[] {EnumFacing.SOUTH, EnumFacing.EAST};
		} else { // NE
			return new EnumFacing[] {EnumFacing.SOUTH, EnumFacing.WEST};
		}
	}
}
