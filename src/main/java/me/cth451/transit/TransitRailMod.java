package me.cth451.transit;

import me.cth451.transit.blocks.FullPlatformPanelBlock;
import me.cth451.transit.items.FareCard;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TransitRailMod implements ModInitializer, ClientModInitializer {

    public static final String NAMESPACE = "transit";
    public static final Item FARE_CARD = new FareCard(new Item.Settings());
    public static final Block FULL_PLATFORM_PANEL_BLOCK = new FullPlatformPanelBlock(FabricBlockSettings.of(Material.METAL, MaterialColor.IRON));
    public static final Item FULL_PLATFORM_PANEL_ITEM = new TallBlockItem(FULL_PLATFORM_PANEL_BLOCK, new Item.Settings().group(ItemGroup.TRANSPORTATION));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "fare_card"), FARE_CARD);
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "full_platform_panel"), FULL_PLATFORM_PANEL_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "full_platform_panel"), FULL_PLATFORM_PANEL_ITEM);
    }

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(FULL_PLATFORM_PANEL_BLOCK, RenderLayer.getCutout());
    }
}
