package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Superclass of any enchantment having to do with leggings
 */
public abstract class LeggingsEnchantment extends ArmorEnchantment {
    public LeggingsEnchantment(String name, int maxLevel, EnchantmentTier tier, EnchantmentTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        Material type = item.getType();
        return type == Material.LEATHER_LEGGINGS ||
                type == Material.CHAINMAIL_LEGGINGS ||
                type == Material.GOLDEN_LEGGINGS ||
                type == Material.IRON_LEGGINGS ||
                type == Material.DIAMOND_LEGGINGS ||
                type == Material.NETHERITE_LEGGINGS;
    }
}
