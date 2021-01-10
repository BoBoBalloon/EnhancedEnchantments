package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Superclass of any enchantment having to do with a chestplate
 */
public abstract class ChestplateEnchantment extends ArmorEnchantment {
    public ChestplateEnchantment(String name, int maxLevel, EnchantmentTier tier, EnchantmentTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        Material type = item.getType();
        return type == Material.LEATHER_CHESTPLATE ||
                type == Material.CHAINMAIL_CHESTPLATE ||
                type == Material.GOLDEN_CHESTPLATE ||
                type == Material.IRON_CHESTPLATE ||
                type == Material.DIAMOND_CHESTPLATE ||
                type == Material.NETHERITE_CHESTPLATE;
    }
}
