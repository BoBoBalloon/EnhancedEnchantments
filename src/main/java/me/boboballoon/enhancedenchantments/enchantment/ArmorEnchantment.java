package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Superclass of any enchantment having to do with armor
 */
public abstract class ArmorEnchantment extends Enchantment {
    public ArmorEnchantment(String name, int maxLevel, EnchantmentTier tier, ArmorTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        Material type = item.getType();
        return type == Material.LEATHER_HELMET ||
                type == Material.LEATHER_CHESTPLATE ||
                type == Material.LEATHER_LEGGINGS ||
                type == Material.LEATHER_BOOTS ||
                type == Material.CHAINMAIL_HELMET ||
                type == Material.CHAINMAIL_CHESTPLATE ||
                type == Material.CHAINMAIL_LEGGINGS ||
                type == Material.CHAINMAIL_BOOTS ||
                type == Material.GOLDEN_HELMET ||
                type == Material.GOLDEN_CHESTPLATE ||
                type == Material.GOLDEN_LEGGINGS ||
                type == Material.GOLDEN_BOOTS ||
                type == Material.IRON_HELMET ||
                type == Material.IRON_CHESTPLATE ||
                type == Material.IRON_LEGGINGS ||
                type == Material.IRON_BOOTS ||
                type == Material.DIAMOND_HELMET ||
                type == Material.DIAMOND_CHESTPLATE ||
                type == Material.DIAMOND_LEGGINGS ||
                type == Material.DIAMOND_BOOTS ||
                type == Material.NETHERITE_HELMET ||
                type == Material.NETHERITE_CHESTPLATE ||
                type == Material.NETHERITE_LEGGINGS ||
                type == Material.NETHERITE_BOOTS;
    }
}
