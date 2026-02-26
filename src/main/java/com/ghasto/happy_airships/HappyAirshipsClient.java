package com.ghasto.happy_airships;

import com.ghasto.happy_airships.harness_armor.HarnessChainmailModel;
import com.ghasto.happy_airships.propeller.PropellerModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

public class HappyAirshipsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(PropellerModel.LAYER_LOCATION, PropellerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(HarnessChainmailModel.LAYER_LOCATION, HarnessChainmailModel::createBodyLayer);
    }
}
