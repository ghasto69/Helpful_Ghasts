package com.ghasto.happy_airships.propeller;

import com.blackgear.vanillabackport.client.level.entities.model.HappyGhastModel;
import com.blackgear.vanillabackport.common.level.entities.happyghast.HappyGhast;
import com.ghasto.happy_airships.HappyAirships;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class PropellerLayer extends RenderLayer<HappyGhast, HappyGhastModel<HappyGhast>> {
    RenderType renderType;
    PropellerModel model;

    public PropellerLayer(RenderLayerParent<HappyGhast, HappyGhastModel<HappyGhast>> renderLayerParent, EntityModelSet modelSet) {
        super(renderLayerParent);
        model = new PropellerModel(modelSet.bakeLayer(PropellerModel.LAYER_LOCATION));
        renderType = RenderType.entityCutoutNoCull(HappyAirships.resource("textures/entity/happy_ghast/propeller.png"));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffers, int packedLight, HappyGhast entity, float f, float g, float partialTicks, float ageInTicks, float k, float l) {
        var accessor = (PropellerDataAccessor) entity;
        if (accessor.getPropeller().isEmpty()) return;

        poseStack.pushPose();

        poseStack.scale(0.5f, 0.5f, 0.5f);

        poseStack.translate(0, 1.5f, 1 + 1/16f);

        poseStack.translate(0, 0.5f, 0);
        float rotation = ageInTicks *
                (entity.hasPassenger(e -> true) ? 1f : 0.1f) *
                20f % 360f;

        poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));
        poseStack.translate(0, -0.5f, 0);

        var vertexConsumer = buffers.getBuffer(renderType);

        if (accessor.getPropeller().hasFoil()) {
            vertexConsumer = VertexMultiConsumer.create(vertexConsumer, buffers.getBuffer(RenderType.entityGlint()));
        }

        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
    }
}
