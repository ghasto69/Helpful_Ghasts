package com.ghasto.happy_airships.mixin;

import com.ghasto.happy_airships.propeller.PropellerDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract void remove(Entity.RemovalReason removalReason);

    @Inject(method = "dropCustomDeathLoot", at = @At("TAIL"))
    private void dropPropellerOnDeath(ServerLevel serverLevel, DamageSource damageSource, boolean bl, CallbackInfo ci) {
        if (!(this instanceof PropellerDataAccessor accessor)) return;
        if (accessor.getPropeller().isEmpty()) return;

        spawnAtLocation(serverLevel, accessor.getPropeller());
        accessor.setPropeller(ItemStack.EMPTY);
    }
}
