package com.ghasto.happy_airships;

import com.ghasto.happy_airships.datagen.HappyAirshipsItemTags;
import com.ghasto.happy_airships.propeller.PropellerItem;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.item.equipment.*;

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

    Item COPPER_PLATED_HARNESS = armoredHarness("copper_plated_harness", ArmorMaterials.COPPER, HappyAirshipsClient.COPPER_PLATED_HARNESS);
    Item IRON_PLATED_HARNESS = armoredHarness("iron_plated_harness", ArmorMaterials.IRON, HappyAirshipsClient.IRON_PLATED_HARNESS);
    Item GOLD_PLATED_HARNESS = armoredHarness("gold_plated_harness", ArmorMaterials.GOLD, HappyAirshipsClient.GOLD_PLATED_HARNESS);
    Item DIAMOND_PLATED_HARNESS = armoredHarness("diamond_plated_harness", ArmorMaterials.DIAMOND, HappyAirshipsClient.DIAMOND_PLATED_HARNESS);
    Item NETHERITE_PLATED_HARNESS = armoredHarness("netherite_plated_harness", ArmorMaterials.NETHERITE, HappyAirshipsClient.NETHERITE_PLATED_HARNESS);

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
                        .build(HappyAirshipsObjects.GLIDE.identifier())
        );
    }

    static Item armoredHarness(String name, ArmorMaterial material, ResourceKey<EquipmentAsset> equipmentAsset) {
        var type = ArmorType.CHESTPLATE;
        var entityGetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.ENTITY_TYPE);

        return item(Item::new, name, new Item.Properties()
                .durability(type.getDurability(material.durability()))
                .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.BODY)
                        .setEquipSound(SoundEvents.HARNESS_EQUIP)
                        .setAsset(equipmentAsset)
                        .setAllowedEntities(entityGetter.getOrThrow(EntityTypeTags.CAN_EQUIP_HARNESS))
                        .setEquipOnInteract(true)
                        .setCanBeSheared(true)
                        .setShearingSound(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.HARNESS_UNEQUIP))
                        .setDamageOnHurt(false)
                        .build()
                )
                .attributes(material.createAttributes(type))
        );
    }

    // Will be useful in later versions of mc
    static <T extends Item> T item(Function<Item.Properties, T> factory, String name, Item.Properties properties) {
        var identifier = HappyAirships.resource(name);
        return Registry.register(BuiltInRegistries.ITEM, identifier, factory.apply(properties.setId(ResourceKey.create(Registries.ITEM, identifier))));
    }

    static void init() {
    }
}
