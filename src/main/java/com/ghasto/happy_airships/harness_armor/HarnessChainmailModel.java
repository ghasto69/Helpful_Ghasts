package com.ghasto.happy_airships.harness_armor;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.blackgear.vanillabackport.common.level.entities.happyghast.HappyGhast;
import com.ghasto.happy_airships.HappyAirships;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class HarnessChainmailModel extends EntityModel<HappyGhast> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(HappyAirships.resource("harness_chainmail"), "main");
	private final ModelPart bb_main;

	public HarnessChainmailModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-15.5F, -13.0F, -15.5F, 31.0F, 13.0F, 31.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int x) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, x);
	}

	@Override
	public void setupAnim(HappyGhast entity, float f, float g, float h, float i, float j) {

	}
}