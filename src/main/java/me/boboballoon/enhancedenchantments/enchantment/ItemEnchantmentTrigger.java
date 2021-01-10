package me.boboballoon.enhancedenchantments.enchantment;

/**
 * Represents all valid enchantment triggers for item related enchantments
 */
public enum ItemEnchantmentTrigger implements EnchantmentTrigger {
    ON_BLOCK_BREAK,
    ON_DURABILITY_LOSS,
    ON_ITEM_BREAK,
    ON_DAMAGE_DEALT,
}
