package com.ghasto.happy_airships.datagen;

import com.blackgear.vanillabackport.core.data.tags.ModItemTags;
import com.ghasto.happy_airships.HappyAirships;
import com.ghasto.happy_airships.HappyAirshipsObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class HappyAirshipsItemTags extends FabricTagProvider.ItemTagProvider {
    public static final TagKey<Item> GLIDE_ENCHANTABLE = TagKey.create(Registries.ITEM, HappyAirships.resource("glide_enchantable"));

    public HappyAirshipsItemTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(GLIDE_ENCHANTABLE)
                .add(HappyAirshipsObjects.PROPELLER);

        getOrCreateTagBuilder(ModItemTags.HARNESSES)
                //.add(HappyAirshipsObjects.COPPER_PLATED_HARNESS)
                .add(HappyAirshipsObjects.IRON_PLATED_HARNESS)
                .add(HappyAirshipsObjects.GOLD_PLATED_HARNESS)
                .add(HappyAirshipsObjects.DIAMOND_PLATED_HARNESS)
                .add(HappyAirshipsObjects.NETHERITE_PLATED_HARNESS);
    }
}
