package me.cth451.transit;

import me.cth451.transit.items.FareTicketItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TransitRailMod implements ModInitializer {

    public static final String NAMESPACE = "transit";
    public static final Item FARE_TICKET = new FareTicketItem(new Item.Settings());

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "fare_ticket"), FARE_TICKET);
    }
}
