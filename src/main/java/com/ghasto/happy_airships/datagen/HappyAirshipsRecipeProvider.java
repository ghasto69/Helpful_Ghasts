package com.ghasto.happy_airships.datagen;

import com.ghasto.happy_airships.HappyAirships;
import com.ghasto.happy_airships.HappyAirshipsObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class HappyAirshipsRecipeProvider extends FabricRecipeProvider {
    public HappyAirshipsRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HappyAirshipsObjects.PROPELLER)
                .pattern(" I ")
                .pattern("ILI")
                .pattern(" I ")
                .define('L', ItemTags.LOGS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(exporter);

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(HappyAirshipsObjects.DIAMOND_PLATED_HARNESS),
                Ingredient.of(Items.NETHERITE_INGOT),
                RecipeCategory.TOOLS,
                HappyAirshipsObjects.NETHERITE_PLATED_HARNESS
        ).unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT)).save(exporter, HappyAirships.resource("netherite_plated_harness"));
    }
}
