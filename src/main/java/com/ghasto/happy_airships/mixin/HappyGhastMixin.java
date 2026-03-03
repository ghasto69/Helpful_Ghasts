package com.ghasto.happy_airships.mixin;

import com.ghasto.happy_airships.HappyAirshipsObjects;
import com.ghasto.happy_airships.propeller.PropellerDataAccessor;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HappyGhast.class)
public abstract class HappyGhastMixin extends Animal implements PropellerDataAccessor {
    protected HappyGhastMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyArg(
            method = "getRiddenInput",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;scale(D)Lnet/minecraft/world/phys/Vec3;")
    )
    private double modifyFlySpeed(double original) {
        if (getPropeller().isEmpty())
            return original;

        var multiplier = 2f;
        for (final var entry : getPropeller().getEnchantments().entrySet()) {
            final var effect = entry.getKey().value().effects().get(HappyAirshipsObjects.GLIDE_EFFECT);
            if (effect == null) continue;
            multiplier = effect.process(entry.getIntValue(), getRandom(), multiplier);
        }
        return original * multiplier;
    }

    @Unique
    private static final EntityDataAccessor<ItemStack> PROPELLER = SynchedEntityData.defineId(HappyGhast.class, EntityDataSerializers.ITEM_STACK);

    @Inject(
            method = "defineSynchedData",
            at = @At("TAIL")
    )
    private void definePropellerSync(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(PROPELLER, ItemStack.EMPTY);
    }

    @Override
    public ItemStack getPropeller() {
        return entityData.get(PROPELLER);
    }

    @Override
    public void setPropeller(ItemStack propeller) {
        entityData.set(PROPELLER, propeller);
    }

    @Inject(
            method = "addAdditionalSaveData",
            at = @At("TAIL")
    )
    private void savePropellerData(ValueOutput tag, CallbackInfo ci) {
        if (getPropeller().isEmpty())
            return;
        tag.store("propeller", ItemStack.CODEC, getPropeller());
    }

    @Inject(
            method = "readAdditionalSaveData",
            at = @At("TAIL")
    )
    private void readPropellerData(ValueInput tag, CallbackInfo ci) {
        var result = tag.read("propeller", ItemStack.CODEC);
        result.ifPresent(this::setPropeller);
    }

    @Inject(
            method = "mobInteract",
            cancellable = true,
            at = @At("HEAD")
    )
    private void shearPropeller(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (getPropeller().isEmpty()) return;

        var itemStack = player.getItemInHand(hand);
        if (!itemStack.is(Items.SHEARS)) return;

        if (!level().isClientSide()) {
            this.spawnAtLocation((ServerLevel) level(), getPropeller(), this.getBbHeight() + 0.5F);
            setPropeller(ItemStack.EMPTY);
            itemStack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
            playSound(SoundEvents.ARMOR_EQUIP_IRON.value());
        }

        cir.setReturnValue(InteractionResult.SUCCESS);
    }
}