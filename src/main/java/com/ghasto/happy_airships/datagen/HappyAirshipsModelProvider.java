package com.ghasto.happy_airships.datagen;

import com.ghasto.happy_airships.HappyAirshipsObjects;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;


public class HappyAirshipsModelProvider extends FabricModelProvider {
    public HappyAirshipsModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(HappyAirshipsObjects.PROPELLER, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(HappyAirshipsObjects.COPPER_PLATED_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HappyAirshipsObjects.IRON_PLATED_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HappyAirshipsObjects.GOLD_PLATED_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HappyAirshipsObjects.DIAMOND_PLATED_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HappyAirshipsObjects.NETHERITE_PLATED_HARNESS, ModelTemplates.FLAT_ITEM);
    }
}
