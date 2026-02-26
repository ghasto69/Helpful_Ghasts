package com.ghasto.happy_airships.mixin;

import com.blackgear.vanillabackport.client.level.entities.model.HappyGhastModel;
import com.blackgear.vanillabackport.client.level.entities.renderer.HappyGhastRenderer;
import com.blackgear.vanillabackport.common.level.entities.happyghast.HappyGhast;
import com.ghasto.happy_airships.HappyAirships;
import com.ghasto.happy_airships.HappyAirshipsObjects;
import com.ghasto.happy_airships.harness_armor.HarnessChainmailLayer;
import com.ghasto.happy_airships.propeller.PropellerLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(HappyGhastRenderer.class)
public abstract class HappyGhastRendererMixin extends MobRenderer<HappyGhast, HappyGhastModel<HappyGhast>> {
    public HappyGhastRendererMixin(EntityRendererProvider.Context context, HappyGhastModel<HappyGhast> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void renderPropeller(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new PropellerLayer(this, context.getModelSet()));
        this.addLayer(new HarnessChainmailLayer(this, context.getModelSet()));
    }

    @ModifyArg(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lcom/blackgear/vanillabackport/client/level/entities/layer/SimpleEquipmentLayer;<init>(Lnet/minecraft/client/renderer/entity/RenderLayerParent;Ljava/util/Map;Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/Predicate;Lnet/minecraft/client/model/EntityModel;Lnet/minecraft/client/model/EntityModel;)V"),
            index = 1
    )
    private Map<ItemStack, ResourceLocation> modifyHarnessMap(Map<ItemStack, ResourceLocation> textureByItem) {
        var map = new HashMap<>(textureByItem);

        //map.put(HappyAirshipsObjects.COPPER_PLATED_HARNESS.getDefaultInstance(), location("copper_plated_harness"));
        map.put(HappyAirshipsObjects.IRON_PLATED_HARNESS.getDefaultInstance(), location("iron_plated_harness"));
        map.put(HappyAirshipsObjects.GOLD_PLATED_HARNESS.getDefaultInstance(), location("gold_plated_harness"));
        map.put(HappyAirshipsObjects.DIAMOND_PLATED_HARNESS.getDefaultInstance(), location("diamond_plated_harness"));
        map.put(HappyAirshipsObjects.NETHERITE_PLATED_HARNESS.getDefaultInstance(), location("netherite_plated_harness"));

        return map;
    }

    @Unique
    private ResourceLocation location(String string) {
        return HappyAirships.resource("textures/entity/happy_ghast/" + string + ".png");
    }
}
