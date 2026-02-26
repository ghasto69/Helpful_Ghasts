package com.ghasto.happy_airships;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;

public final class HappyAirshipsLootModifiers {
    private HappyAirshipsLootModifiers() {
    }

    public static void init() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (!source.isBuiltin()) {
                return;
            }

            ArmorWeights weights = armorWeightsFor(key);
            if (weights == null) {
                return;
            }

            // Add harness entries into the first existing chest pool (same roll competition as horse armor).
            tableBuilder.modifyPools(new java.util.function.Consumer<>() {
                private boolean modified;

                @Override
                public void accept(LootPool.Builder poolBuilder) {
                    if (modified) {
                        return;
                    }

                    addHarnessEntries(poolBuilder, weights);
                    modified = true;
                }
            });
        });
    }

    private static ArmorWeights armorWeightsFor(ResourceKey<LootTable> key) {
        if (key.equals(BuiltInLootTables.ANCIENT_CITY)) {
            return new ArmorWeights(0, 0, 2);
        }

        if (key.equals(BuiltInLootTables.RUINED_PORTAL)) {
            return new ArmorWeights(0, 5, 0);
        }

        if (key.equals(BuiltInLootTables.NETHER_BRIDGE)) {
            return new ArmorWeights(5, 8, 3);
        }

        if (key.equals(BuiltInLootTables.SIMPLE_DUNGEON) || key.equals(BuiltInLootTables.DESERT_PYRAMID)) {
            return new ArmorWeights(15, 10, 5);
        }

        if (key.equals(BuiltInLootTables.END_CITY_TREASURE)
                || key.equals(BuiltInLootTables.STRONGHOLD_CORRIDOR)
                || key.equals(BuiltInLootTables.JUNGLE_TEMPLE)
                || key.equals(BuiltInLootTables.VILLAGE_WEAPONSMITH)) {
            return new ArmorWeights(1, 1, 1);
        }

        return null;
    }

    private static void addHarnessEntries(LootPool.Builder pool, ArmorWeights weights) {
        int ironWeight = weights.iron();
        int goldWeight = weights.gold();
        int diamondWeight = weights.diamond();

        // Uncomment once copper harness is enabled.
        // int copperWeight = ironWeight;
        // if (copperWeight > 0) {
        //     pool.add(
        //             copperWeight == 1
        //                     ? LootItem.lootTableItem(HappyAirshipsObjects.COPPER_PLATED_HARNESS)
        //                     : LootItem.lootTableItem(HappyAirshipsObjects.COPPER_PLATED_HARNESS).setWeight(copperWeight)
        //     );
        // }

        if (ironWeight > 0) {
            pool.add(
                    ironWeight == 1
                            ? LootItem.lootTableItem(HappyAirshipsObjects.IRON_PLATED_HARNESS)
                            : LootItem.lootTableItem(HappyAirshipsObjects.IRON_PLATED_HARNESS).setWeight(ironWeight)
            );
        }

        if (goldWeight > 0) {
            pool.add(
                    goldWeight == 1
                            ? LootItem.lootTableItem(HappyAirshipsObjects.GOLD_PLATED_HARNESS)
                            : LootItem.lootTableItem(HappyAirshipsObjects.GOLD_PLATED_HARNESS).setWeight(goldWeight)
            );
        }

        if (diamondWeight > 0) {
            pool.add(
                    diamondWeight == 1
                            ? LootItem.lootTableItem(HappyAirshipsObjects.DIAMOND_PLATED_HARNESS)
                            : LootItem.lootTableItem(HappyAirshipsObjects.DIAMOND_PLATED_HARNESS).setWeight(diamondWeight)
            );
        }
    }

    private record ArmorWeights(int iron, int gold, int diamond) {
    }
}
