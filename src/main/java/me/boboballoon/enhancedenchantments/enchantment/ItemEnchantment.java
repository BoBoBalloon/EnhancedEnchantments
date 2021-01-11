package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Superclass of any enchantment having to do with an item
 */
public abstract class ItemEnchantment extends Enchantment {
    public ItemEnchantment(String name, int maxLevel, EnchantmentTier tier, ItemTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }

    @Override
    public String applicableTo() {
        return "items";
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
                type == Material.NETHERITE_SWORD;
    }
}
