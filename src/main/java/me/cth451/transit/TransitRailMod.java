package me.cth451.transit;

import me.cth451.transit.items.FareCard;
import me.cth451.transit.items.FullPlatformPanelItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TransitRailMod implements ModInitializer {

    public static final String NAMESPACE = "transit";
    public static final Item FARE_CARD = new FareCard(new Item.Settings());
    public static final Item FULL_PLATFORM_PANEL_ITEM = new FullPlatformPanelItem(new Item.Settings());

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "fare_card"), FARE_CARD);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "full_platform_panel_item"), FULL_PLATFORM_PANEL_ITEM);
    }
}
