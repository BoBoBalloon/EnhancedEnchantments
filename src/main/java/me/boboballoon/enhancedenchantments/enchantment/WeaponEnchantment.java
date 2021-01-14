package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Superclass of any enchantment having to do with a weapon
 */
public abstract class WeaponEnchantment extends ItemEnchantment {
    public WeaponEnchantment(String name, int maxLevel, EnchantmentTier tier, ItemTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }

    @Override
    public String applicableTo() {
        return "weapons";
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        Material type = item.getType();
        return type == Material.WOODEN_SWORD ||
                type == Material.STONE_SWORD ||
                type == Material.GOLDEN_SWORD ||
                type == Material.IRON_SWORD ||
                type == Material.DIAMOND_SWORD ||
                type == Material.NETHERITE_SWORD ||
                type == Material.WOODEN_AXE ||
                type == Material.STONE_AXE ||
                type == Material.GOLDEN_AXE ||
                type == Material.IRON_AXE ||
                type == Material.DIAMOND_AXE ||
                type == Material.NETHERITE_AXE;
    }
}
