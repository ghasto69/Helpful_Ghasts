package com.ghasto.happy_airships.mixin;

import com.ghasto.happy_airships.propeller.PropellerDataAccessor;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(HappyGhastRenderState.class)
public class HappyGhastRenderStateMixin implements PropellerDataAccessor {
    @Unique
    private ItemStack propeller = ItemStack.EMPTY;

    @Override
    public ItemStack getPropeller() {
        return this.propeller;
    }

    @Override
    public void setPropeller(ItemStack propeller) {
        this.propeller = propeller;
    }
}
