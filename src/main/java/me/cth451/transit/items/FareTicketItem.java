package me.cth451.transit.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class FareTicketItem extends Item {
    public FareTicketItem(Settings settings) {
        super(settings.group(ItemGroup.TRANSPORTATION));
    }
}
