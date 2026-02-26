package com.ghasto.happy_airships.harness_armor;

import com.blackgear.vanillabackport.client.level.entities.model.HappyGhastModel;
import com.blackgear.vanillabackport.common.level.entities.happyghast.HappyGhast;
import com.ghasto.happy_airships.HappyAirships;
import com.ghasto.happy_airships.HappyAirshipsObjects;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;

import java.util.Map;

public class HarnessChainmailLayer extends RenderLayer<HappyGhast, HappyGhastModel<HappyGhast>> {
    public static final Map<Item, ResourceLocation> TEXTURES = Map.of(
            HappyAirshipsObjects.IRON_PLATED_HARNESS, HappyAirships.resource("textures/entity/happy_ghast/iron_chainmail.png"),
            HappyAirshipsObjects.GOLD_PLATED_HARNESS, HappyAirships.resource("textures/entity/happy_ghast/gold_chainmail.png"),
            HappyAirshipsObjects.DIAMOND_PLATED_HARNESS, HappyAirships.resource("textures/entity/happy_ghast/diamond_chainmail.png"),
            HappyAirshipsObjects.NETHERITE_PLATED_HARNESS, HappyAirships.resource("textures/entity/happy_ghast/netherite_chainmail.png")
    );

    HarnessChainmailModel model;

    public HarnessChainmailLayer(RenderLayerParent<HappyGhast, HappyGhastModel<HappyGhast>> renderLayerParent, EntityModelSet modelSet) {
        super(renderLayerParent);

        this.model = new HarnessChainmailModel(modelSet.bakeLayer(HarnessChainmailModel.LAYER_LOCATION));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffers, int light, HappyGhast entity, float f, float g, float h, float j, float k, float l) {
        var harness = entity.getItemBySlot(EquipmentSlot.CHEST);
        if(harness.isEmpty() || !TEXTURES.containsKey(harness.getItem())) return;

        var texture = TEXTURES.get(harness.getItem());
        var renderType = RenderType.entityCutoutNoCull(texture);

        poseStack.pushPose();

        poseStack.scale(0.5f, 0.5f, 0.5f);

        poseStack.mulPose(Axis.YP.rotationDegrees(180));

        poseStack.translate(0, 1.5f, 0);
        this.model.renderToBuffer(poseStack, buffers.getBuffer(renderType), light, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
    }
}
