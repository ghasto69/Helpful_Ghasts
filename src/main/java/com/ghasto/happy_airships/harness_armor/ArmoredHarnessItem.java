package com.ghasto.happy_airships.harness_armor;

import com.blackgear.vanillabackport.common.level.entities.happyghast.HappyGhast;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class ArmoredHarnessItem extends ArmorItem {
    public ArmoredHarnessItem(Holder<ArmorMaterial> holder, Type type, Properties properties) {
        super(holder, type, properties);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        if (livingEntity instanceof HappyGhast ghast) {
            if (livingEntity.isAlive() && !ghast.isHarnessed() && ghast.canBeHarnessed()) {
                if (!player.level().isClientSide) {
                    ghast.equipHarness();
                    ghast.setItemSlot(EquipmentSlot.CHEST, itemStack);
                    livingEntity.level().gameEvent(livingEntity, GameEvent.EQUIP, livingEntity.position());
                    itemStack.shrink(1);
                }

                return InteractionResult.sidedSuccess(player.level().isClientSide);
            }
        }

        return InteractionResult.PASS;
    }
}
