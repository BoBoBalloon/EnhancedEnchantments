package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Superclass of any enchantment having to do with boots
 */
public abstract class BootsEnchantment extends ArmorEnchantment {
    public BootsEnchantment(String name, int maxLevel, EnchantmentTier tier, EnchantmentTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        Material type = item.getType();
        return type == Material.LEATHER_BOOTS ||
                type == Material.CHAINMAIL_BOOTS ||
                type == Material.GOLDEN_BOOTS ||
                type == Material.IRON_BOOTS ||
                type == Material.DIAMOND_BOOTS ||
                type == Material.NETHERITE_BOOTS;
    }
}
