package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class AxeEnchantment extends WeaponEnchantment {
    public AxeEnchantment(String name, int maxLevel, EnchantmentTier tier, ItemTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }

    @Override
    public String applicableTo() {
        return "axes";
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        Material type = item.getType();
        return type == Material.WOODEN_AXE ||
                type == Material.STONE_AXE ||
                type == Material.GOLDEN_AXE ||
                type == Material.IRON_AXE ||
                type == Material.DIAMOND_AXE ||
                type == Material.NETHERITE_AXE;
    }
}
