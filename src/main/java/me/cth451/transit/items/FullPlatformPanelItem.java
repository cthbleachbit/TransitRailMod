package me.cth451.transit.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class FullPlatformPanelItem extends Item {
    public FullPlatformPanelItem(Settings settings) {
        super(settings.group(ItemGroup.TRANSPORTATION));
    }
}
