package com.ghasto.happy_airships.datagen;

import com.ghasto.happy_airships.HappyAirships;
import com.ghasto.happy_airships.HappyAirshipsObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class HappyAirshipsItemTags extends FabricTagsProvider.ItemTagsProvider {
    public static final TagKey<Item> GLIDE_ENCHANTABLE = TagKey.create(Registries.ITEM, HappyAirships.resource("glide_enchantable"));

    public HappyAirshipsItemTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(GLIDE_ENCHANTABLE)
                .add(key(HappyAirshipsObjects.PROPELLER));

        builder(ItemTags.HARNESSES)
                .add(key(HappyAirshipsObjects.COPPER_PLATED_HARNESS))
                .add(key(HappyAirshipsObjects.IRON_PLATED_HARNESS))
                .add(key(HappyAirshipsObjects.GOLD_PLATED_HARNESS))
                .add(key(HappyAirshipsObjects.DIAMOND_PLATED_HARNESS))
                .add(key(HappyAirshipsObjects.NETHERITE_PLATED_HARNESS));
    }

    private static ResourceKey<Item> key(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow();
    }
}
