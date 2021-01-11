package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Superclass of any enchantment having to do with a helmet
 */
public abstract class HelmetEnchantment extends ArmorEnchantment {
    public HelmetEnchantment(String name, int maxLevel, EnchantmentTier tier, ArmorTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        Material type = item.getType();
        return type == Material.LEATHER_HELMET ||
                type == Material.CHAINMAIL_HELMET ||
                type == Material.GOLDEN_HELMET ||
                type == Material.IRON_HELMET ||
                type == Material.DIAMOND_HELMET ||
                type == Material.NETHERITE_HELMET;
    }
}
