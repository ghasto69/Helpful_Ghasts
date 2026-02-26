package com.ghasto.happy_airships;

import com.ghasto.happy_airships.datagen.HappyAirshipsDynamicRegistryProvider;
import com.ghasto.happy_airships.datagen.HappyAirshipsItemTags;
import com.ghasto.happy_airships.datagen.HappyAirshipsModelProvider;
import com.ghasto.happy_airships.datagen.HappyAirshipsRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class HappyAirshipsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		var pack = generator.createPack();
		pack.addProvider(HappyAirshipsModelProvider::new);
		pack.addProvider(HappyAirshipsDynamicRegistryProvider::new);
		pack.addProvider(HappyAirshipsItemTags::new);
		pack.addProvider(HappyAirshipsRecipeProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.ENCHANTMENT, HappyAirshipsObjects::generateEnchantments);
	}
}
