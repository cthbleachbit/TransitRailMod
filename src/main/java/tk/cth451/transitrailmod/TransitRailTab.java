package tk.cth451.transitrailmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import tk.cth451.transitrailmod.init.ModBlocks;

public class TransitRailTab extends CreativeTabs{

	public TransitRailTab(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(ModBlocks.logo_block);
	}
	
}
