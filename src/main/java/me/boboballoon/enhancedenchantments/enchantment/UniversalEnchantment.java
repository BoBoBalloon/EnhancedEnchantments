package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Superclass of any enchantment that can be used on both armor and items
 */
public abstract class UniversalEnchantment extends Enchantment {
    public UniversalEnchantment(String name, int maxLevel, EnchantmentTier tier, EnchantmentTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        Material type = item.getType();
        return type == Material.WOODEN_AXE ||
                type == Material.WOODEN_PICKAXE ||
                type == Material.WOODEN_HOE ||
                type == Material.WOODEN_SHOVEL ||
                type == Material.STONE_AXE ||
                type == Material.STONE_PICKAXE ||
                type == Material.STONE_HOE ||
                type == Material.STONE_SHOVEL ||
                type == Material.GOLDEN_AXE ||
                type == Material.GOLDEN_PICKAXE ||
                type == Material.GOLDEN_HOE ||
                type == Material.GOLDEN_SHOVEL ||
                type == Material.IRON_AXE ||
                type == Material.IRON_PICKAXE ||
                type == Material.IRON_HOE ||
                type == Material.IRON_SHOVEL ||
                type == Material.DIAMOND_AXE ||
                type == Material.DIAMOND_PICKAXE ||
                type == Material.DIAMOND_HOE ||
                type == Material.DIAMOND_SHOVEL ||
                type == Material.NETHERITE_AXE ||
                type == Material.NETHERITE_PICKAXE ||
                type == Material.NETHERITE_HOE ||
                type == Material.NETHERITE_SHOVEL ||
                type == Material.WOODEN_SWORD ||
                type == Material.STONE_SWORD ||
                type == Material.GOLDEN_SWORD ||
                type == Material.IRON_SWORD ||
                type == Material.DIAMOND_SWORD ||
                type == Material.NETHERITE_SWORD ||
                type == Material.LEATHER_HELMET ||
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
