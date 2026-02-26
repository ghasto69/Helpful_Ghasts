package com.ghasto.happy_airships.propeller;

import com.blackgear.vanillabackport.common.level.entities.happyghast.HappyGhast;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PropellerItem extends Item {
    public PropellerItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        if(interactionHand == InteractionHand.OFF_HAND) return InteractionResult.PASS;

        // Is ghast with harness
        if(!(livingEntity instanceof HappyGhast happyGhast)) return InteractionResult.PASS;
        if(!happyGhast.isHarnessed()) return InteractionResult.PASS;

        // Does not have a propeller
        var accessor = (PropellerDataAccessor) happyGhast;
        if(!accessor.getPropeller().isEmpty()) return InteractionResult.PASS;

        if(!player.level().isClientSide()) {
            accessor.setPropeller(itemStack.copyWithCount(1));

            if(!player.isCreative())
                itemStack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}
