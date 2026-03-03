package com.ghasto.happy_airships.propeller;

import com.ghasto.happy_airships.HappyAirships;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.animal.ghast.HappyGhastModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class PropellerLayer extends RenderLayer<HappyGhastRenderState, HappyGhastModel> {
    RenderType renderType;
    PropellerModel model;

    public PropellerLayer(RenderLayerParent<HappyGhastRenderState, HappyGhastModel> renderLayerParent, EntityModelSet modelSet) {
        super(renderLayerParent);
        model = new PropellerModel(modelSet.bakeLayer(PropellerModel.LAYER_LOCATION));
        renderType = RenderTypes.entityCutout(HappyAirships.resource("textures/entity/happy_ghast/propeller.png"));
    }


    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, HappyGhastRenderState state, float yRot, float xRot) {
        var accessor = (PropellerDataAccessor) state;
        if (accessor.getPropeller().isEmpty()) return;

        poseStack.pushPose();

        poseStack.scale(2f, 2f, 2f);

        poseStack.translate(0, -12 / 16f, 1 + 1 / 16f);

        poseStack.translate(0, 0.5f, 0);
        float rotation = state.ageInTicks *
                (state.isRidden ? 1f : 0.1f) *
                20f % 360f;

        poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));
        poseStack.translate(0, -0.5f, 0);

        submitNodeCollector.submitModel(model, state, poseStack, renderType, lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        if (accessor.getPropeller().hasFoil()) {
            submitNodeCollector.submitModel(model, state, poseStack, RenderTypes.entityGlint(), lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        }

        poseStack.popPose();
    }
}
