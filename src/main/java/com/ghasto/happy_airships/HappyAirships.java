package com.ghasto.happy_airships;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HappyAirships implements ModInitializer {
    public static final String MOD_ID = "happy_airships";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        HappyAirshipsObjects.init();
        HappyAirshipsLootModifiers.init();

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(HappyAirshipsObjects.PROPELLER);

            entries.accept(HappyAirshipsObjects.COPPER_PLATED_HARNESS);
            entries.accept(HappyAirshipsObjects.IRON_PLATED_HARNESS);
            entries.accept(HappyAirshipsObjects.GOLD_PLATED_HARNESS);
            entries.accept(HappyAirshipsObjects.DIAMOND_PLATED_HARNESS);
            entries.accept(HappyAirshipsObjects.NETHERITE_PLATED_HARNESS);
        });
    }

    public static Identifier resource(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
