package me.cth451.transit;

import me.cth451.transit.blockentities.TurnstileBlockEntity;
import me.cth451.transit.blocks.*;
import me.cth451.transit.items.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
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
    public static final Block FULL_PLATFORM_DOOR_BLOCK = new FullPlatformDoorBlock(FabricBlockSettings.of(Material.METAL, MaterialColor.IRON));
    public static final Item FULL_PLATFORM_DOOR_ITEM = new TallBlockItem(FULL_PLATFORM_DOOR_BLOCK, new Item.Settings().group(ItemGroup.TRANSPORTATION));
    public static final Block TURNSTILE_BLOCK = new Turnstile(FabricBlockSettings.of(Material.METAL, MaterialColor.IRON));
    public static final Item TURNSTILE_ITEM = new BlockItem(TURNSTILE_BLOCK, new Item.Settings().group(ItemGroup.TRANSPORTATION));

    public static BlockEntityType<TurnstileBlockEntity> TURNSTILE_BLOCK_ENTITY;

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "fare_card"), FARE_CARD);
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "full_platform_panel"), FULL_PLATFORM_PANEL_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "full_platform_panel"), FULL_PLATFORM_PANEL_ITEM);
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "full_platform_door"), FULL_PLATFORM_DOOR_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "full_platform_door"), FULL_PLATFORM_DOOR_ITEM);
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "turnstile"), TURNSTILE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "turnstile"), TURNSTILE_ITEM);

        TURNSTILE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(NAMESPACE, "turnstile_entity"), BlockEntityType.Builder.create(TurnstileBlockEntity::new, TURNSTILE_BLOCK).build(null));
    }

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(FULL_PLATFORM_PANEL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FULL_PLATFORM_DOOR_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TURNSTILE_BLOCK, RenderLayer.getCutout());
    }
}
