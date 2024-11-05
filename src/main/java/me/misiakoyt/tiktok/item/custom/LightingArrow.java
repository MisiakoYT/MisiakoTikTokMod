package me.misiakoyt.tiktok.item.custom;

import me.misiakoyt.tiktok.entity.projectile.LightingArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LightingArrow extends ArrowItem {

    public LightingArrow(Item.Settings settings) {
        super(settings);
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new LightingArrowEntity(world, shooter, stack.copyWithCount(1));
    }

}
