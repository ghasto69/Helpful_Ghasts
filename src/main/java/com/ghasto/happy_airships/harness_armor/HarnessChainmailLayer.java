package com.ghasto.happy_airships.harness_armor;

import com.ghasto.happy_airships.HappyAirships;
import com.ghasto.happy_airships.HappyAirshipsObjects;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.animal.ghast.HappyGhastModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.Map;

public class HarnessChainmailLayer extends RenderLayer<HappyGhastRenderState, HappyGhastModel> {
    public static final Map<Item, Identifier> TEXTURES = Map.of(
            HappyAirshipsObjects.COPPER_PLATED_HARNESS, HappyAirships.resource("textures/entity/happy_ghast/copper_chainmail.png"),
            HappyAirshipsObjects.IRON_PLATED_HARNESS, HappyAirships.resource("textures/entity/happy_ghast/iron_chainmail.png"),
            HappyAirshipsObjects.GOLD_PLATED_HARNESS, HappyAirships.resource("textures/entity/happy_ghast/gold_chainmail.png"),
            HappyAirshipsObjects.DIAMOND_PLATED_HARNESS, HappyAirships.resource("textures/entity/happy_ghast/diamond_chainmail.png"),
            HappyAirshipsObjects.NETHERITE_PLATED_HARNESS, HappyAirships.resource("textures/entity/happy_ghast/netherite_chainmail.png")
    );

    HarnessChainmailModel model;

    public HarnessChainmailLayer(RenderLayerParent<HappyGhastRenderState, HappyGhastModel> renderLayerParent, EntityModelSet modelSet) {
        super(renderLayerParent);

        this.model = new HarnessChainmailModel(modelSet.bakeLayer(HarnessChainmailModel.LAYER_LOCATION));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, HappyGhastRenderState state, float yRot, float xRot) {
        var harness = state.bodyItem;
        if (harness.isEmpty() || !TEXTURES.containsKey(harness.getItem())) return;

        var texture = TEXTURES.get(harness.getItem());
        var renderType = RenderTypes.entityCutout(texture);

        poseStack.pushPose();

        poseStack.scale(2f, 2f, 2f);

        poseStack.mulPose(Axis.YP.rotationDegrees(180));

        poseStack.translate(0, -12 / 16f, 0);

        submitNodeCollector.submitModel(model, state, poseStack, renderType, lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);

        poseStack.popPose();
    }
}
