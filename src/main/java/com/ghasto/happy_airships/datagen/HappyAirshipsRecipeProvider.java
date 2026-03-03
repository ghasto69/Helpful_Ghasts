package com.ghasto.happy_airships.datagen;

import com.ghasto.happy_airships.HappyAirships;
import com.ghasto.happy_airships.HappyAirshipsObjects;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class HappyAirshipsRecipeProvider extends FabricRecipeProvider {
    public HappyAirshipsRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                ShapedRecipeBuilder.shaped(provider.lookupOrThrow(Registries.ITEM), RecipeCategory.TOOLS, HappyAirshipsObjects.PROPELLER)
                        .pattern(" I ")
                        .pattern("ILI")
                        .pattern(" I ")
                        .define('L', ItemTags.LOGS)
                        .define('I', Items.IRON_INGOT)
                        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                        .save(recipeOutput);

                SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(HappyAirshipsObjects.DIAMOND_PLATED_HARNESS),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.TOOLS,
                        HappyAirshipsObjects.NETHERITE_PLATED_HARNESS
                ).unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT)).save(recipeOutput, ResourceKey.create(Registries.RECIPE, HappyAirships.resource("netherite_plated_harness")));
            }
        };
    }

    @Override
    public String getName() {
        return "Recipes";
    }
}
