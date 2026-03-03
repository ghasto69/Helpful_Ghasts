package com.ghasto.happy_airships.mixin;


import com.ghasto.happy_airships.harness_armor.HarnessChainmailLayer;
import com.ghasto.happy_airships.propeller.PropellerDataAccessor;
import com.ghasto.happy_airships.propeller.PropellerLayer;
import net.minecraft.client.model.animal.ghast.HappyGhastModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HappyGhastRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HappyGhastRenderer.class)
public abstract class HappyGhastRendererMixin extends MobRenderer<HappyGhast, HappyGhastRenderState, HappyGhastModel> {
    public HappyGhastRendererMixin(EntityRendererProvider.Context context, HappyGhastModel model, float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void renderPropeller(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new PropellerLayer(this, context.getModelSet()));
        this.addLayer(new HarnessChainmailLayer(this, context.getModelSet()));
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/animal/happyghast/HappyGhast;Lnet/minecraft/client/renderer/entity/state/HappyGhastRenderState;F)V", at = @At("TAIL"))
    void addPropellerToState(HappyGhast entity, HappyGhastRenderState state, float partialTicks, CallbackInfo ci) {
        var accessorEntity = (PropellerDataAccessor) entity;
        var accessorRenderState = (PropellerDataAccessor) state;
        accessorRenderState.setPropeller(accessorEntity.getPropeller());
    }
}
