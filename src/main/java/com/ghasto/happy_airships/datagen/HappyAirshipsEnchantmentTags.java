package com.ghasto.happy_airships.datagen;

import com.ghasto.happy_airships.HappyAirshipsObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class HappyAirshipsEnchantmentTags extends EnchantmentTagsProvider {
    public HappyAirshipsEnchantmentTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EnchantmentTags.TRADEABLE)
                .add(HappyAirshipsObjects.GLIDE);
    }
}
