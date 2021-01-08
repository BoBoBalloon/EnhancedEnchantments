package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class WeaponEnchantment extends ItemEnchantment {
    public WeaponEnchantment(String name, int maxLevel, EnchantmentTier tier, ItemEnchantmentTrigger trigger) {
        super(name, maxLevel, tier, trigger);
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        Material type = item.getType();
        return type == Material.WOODEN_SWORD ||
                type == Material.STONE_SWORD ||
                type == Material.GOLDEN_SWORD ||
                type == Material.IRON_SWORD ||
                type == Material.DIAMOND_SWORD ||
                type == Material.NETHERITE_SWORD;
    }
}
