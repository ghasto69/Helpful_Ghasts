package com.ghasto.happy_airships;

import com.blackgear.vanillabackport.common.level.items.HarnessItem;
import com.ghasto.happy_airships.datagen.HappyAirshipsItemTags;
import com.ghasto.happy_airships.propeller.PropellerItem;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
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

    //ArmoredHarnessItem COPPER_PLATED_HARNESS = armoredHarness("copper_plated_harness",ModArmorMaterials.COPPER.orElseThrow(), 5);
    HarnessItem IRON_PLATED_HARNESS = armoredHarness("iron_plated_harness", ArmorMaterials.IRON);
    HarnessItem GOLD_PLATED_HARNESS = armoredHarness("gold_plated_harness", ArmorMaterials.GOLD);
    HarnessItem DIAMOND_PLATED_HARNESS = armoredHarness("diamond_plated_harness", ArmorMaterials.DIAMOND);
    HarnessItem NETHERITE_PLATED_HARNESS = armoredHarness("netherite_plated_harness", ArmorMaterials.NETHERITE);

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

    static HarnessItem armoredHarness(String name, Holder<ArmorMaterial> material) {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        var type = ArmorItem.Type.CHESTPLATE;
        var slot = EquipmentSlotGroup.bySlot(type.getSlot());
        var resourceLocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());

        var defense = material.value().getDefense(type);
        var toughness = material.value().toughness();

        builder.add(Attributes.ARMOR, new AttributeModifier(resourceLocation, defense, AttributeModifier.Operation.ADD_VALUE), slot);
        builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourceLocation, toughness, AttributeModifier.Operation.ADD_VALUE), slot);
        float knockbackResistance = (material.value()).knockbackResistance();
        if (knockbackResistance > 0.0F) {
            builder.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(resourceLocation, knockbackResistance, AttributeModifier.Operation.ADD_VALUE), slot);
        }

        return item(HarnessItem::new, name, new Item.Properties().attributes(builder.build()).stacksTo(1));
    }

    // Will be useful in later versions of mc
    static <T extends Item> T item(Function<Item.Properties, T> factory, String name, Item.Properties properties) {
        return Registry.register(BuiltInRegistries.ITEM, HappyAirships.resource(name), factory.apply(properties));
    }

    static void init() {
    }
}
