package com.ghasto.happy_airships.mixin;

import com.ghasto.happy_airships.harness_armor.ArmoredHarnessItem;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.screens.inventory.SmithingScreen;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SmithingScreen.class)
public class SmithingScreenMixin {
    //This stops the instanceOf from returning true if it is a Happy Ghast armor
    @ModifyExpressionValue(
            method = "updateArmorStandPreview",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;")
    )
    private Item modifyValue(Item original) {
        return original instanceof ArmoredHarnessItem ? null : original;
    }
}
