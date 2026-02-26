package com.ghasto.happy_airships;

import com.ghasto.happy_airships.datagen.HappyAirshipsItemTags;
import com.ghasto.happy_airships.harness_armor.ArmoredHarnessItem;
import com.ghasto.happy_airships.propeller.PropellerItem;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

import java.util.function.Function;

public interface HappyAirshipsObjects {
    ResourceKey<Enchantment> GLIDE = ResourceKey.create(Registries.ENCHANTMENT, HappyAirships.resource("glide"));
    DataComponentType<EnchantmentValueEffect> GLIDE_EFFECT = Registry.register(
            BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE,
            HappyAirships.resource("glide_effect"),
            DataComponentType.<EnchantmentValueEffect>builder().persistent(EnchantmentValueEffect.CODEC).build()
    );

    PropellerItem PROPELLER = item(PropellerItem::new, "propeller", new Item.Properties().stacksTo(1));



    //durability values from Items.java

    //ArmoredHarnessItem COPPER_PLATED_HARNESS = armoredHarness("copper_plated_harness",ModArmorMaterials.COPPER.orElseThrow(), 5);
    ArmoredHarnessItem IRON_PLATED_HARNESS = armoredHarness("iron_plated_harness", ArmorMaterials.IRON, 15);
    ArmoredHarnessItem GOLD_PLATED_HARNESS = armoredHarness("gold_plated_harness", ArmorMaterials.GOLD, 7);
    ArmoredHarnessItem DIAMOND_PLATED_HARNESS = armoredHarness("diamond_plated_harness", ArmorMaterials.DIAMOND, 33);
    ArmoredHarnessItem NETHERITE_PLATED_HARNESS = armoredHarness("netherite_plated_harness", ArmorMaterials.NETHERITE, 37);

    static void generateEnchantments(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);
        context.register(
            HappyAirshipsObjects.GLIDE,
            Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(HappyAirshipsItemTags.GLIDE_ENCHANTABLE),
                10,
                2,
                Enchantment.dynamicCost(1, 11),
                Enchantment.dynamicCost(21, 11),
                1,
                EquipmentSlotGroup.BODY
            ))
            .withSpecialEffect(HappyAirshipsObjects.GLIDE_EFFECT, new AddValue(LevelBasedValue.perLevel(0.75F)))
            .build(HappyAirshipsObjects.GLIDE.location())
        );
    }

    static ArmoredHarnessItem armoredHarness(String name, Holder<ArmorMaterial> material, int durability) {
        var type = ArmorItem.Type.CHESTPLATE;
        return item(p -> new ArmoredHarnessItem(material, type, p), name, new Item.Properties().durability(type.getDurability(durability)));
    }

    // Will be useful in later versions of mc
    static <T extends Item> T item(Function<Item.Properties, T> factory, String name, Item.Properties properties) {
        return Registry.register(BuiltInRegistries.ITEM, HappyAirships.resource(name), factory.apply(properties));
    }

    static void init() {
    }
}
