package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Superclass of any enchantment having to do with a hoe
 */
public abstract class HoeEnchantment extends ToolEnchantment {
    public HoeEnchantment(String name, int maxLevel, EnchantmentTier tier, ItemTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }

    @Override
    public String applicableTo() {
        return "hoes";
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        Material type = item.getType();
        return type == Material.WOODEN_HOE ||
                type == Material.STONE_HOE ||
                type == Material.IRON_HOE ||
                type == Material.GOLDEN_HOE ||
                type == Material.DIAMOND_HOE ||
                type == Material.NETHERITE_HOE;
    }
}
