package com.ghasto.happy_airships.propeller;

import net.minecraft.world.item.ItemStack;

public interface PropellerDataAccessor {
    ItemStack getPropeller();
    void setPropeller(ItemStack propeller);
}
