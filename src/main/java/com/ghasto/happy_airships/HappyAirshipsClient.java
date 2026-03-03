package com.ghasto.happy_airships;

import com.ghasto.happy_airships.harness_armor.HarnessChainmailModel;
import com.ghasto.happy_airships.propeller.PropellerModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class HappyAirshipsClient implements ClientModInitializer {
    public static final ResourceKey<EquipmentAsset> COPPER_PLATED_HARNESS = asset("copper_plated_harness");
    public static final ResourceKey<EquipmentAsset> IRON_PLATED_HARNESS = asset("iron_plated_harness");
    public static final ResourceKey<EquipmentAsset> GOLD_PLATED_HARNESS = asset("gold_plated_harness");
    public static final ResourceKey<EquipmentAsset> DIAMOND_PLATED_HARNESS = asset("diamond_plated_harness");
    public static final ResourceKey<EquipmentAsset> NETHERITE_PLATED_HARNESS = asset("netherite_plated_harness");

    private static ResourceKey<EquipmentAsset> asset(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, HappyAirships.resource(name));
    }

    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(PropellerModel.LAYER_LOCATION, PropellerModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(HarnessChainmailModel.LAYER_LOCATION, HarnessChainmailModel::createBodyLayer);
    }
}
