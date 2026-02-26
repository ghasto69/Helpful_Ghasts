package com.ghasto.happy_airships.propeller;// Made with Blockbench 5.0.7
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

public class PropellerModel extends EntityModel<HappyGhast> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(HappyAirships.resource("propeller"), "main");
	private final ModelPart bb_main;

	public PropellerModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -32.0F, 0.0F, 32.0F, 32.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 32).addBox(0.0F, -18.0F, -1.0F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(-1, 32).addBox(-2.0F, -16.0F, -1.0F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int x) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, x);
	}

	@Override
	public void setupAnim(HappyGhast entity, float f, float g, float h, float i, float j) {

	}
}
