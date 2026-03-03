package com.ghasto.happy_airships.mixin;

import com.ghasto.happy_airships.propeller.PropellerDataAccessor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(
            method = "attemptToShearEquipment",
            at = @At("HEAD"),
            cancellable = true)
    private void dontShearIfPropeller(Player player, InteractionHand hand, ItemStack heldItem, Mob target, CallbackInfoReturnable<Boolean> cir) {
        if (!(target instanceof PropellerDataAccessor accessor)) return;
        if (accessor.getPropeller().isEmpty()) return;

        cir.setReturnValue(false);
    }
}
