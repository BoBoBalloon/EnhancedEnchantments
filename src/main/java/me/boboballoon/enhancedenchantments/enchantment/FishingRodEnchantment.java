package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Superclass of any enchantment having to do with an fishing rod
 */
public abstract class FishingRodEnchantment extends Enchantment {
    public FishingRodEnchantment(String name, int maxLevel, EnchantmentTier tier, FishingTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }

    @Override
    public String applicableTo() {
        return "fishing rods";
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        return (item.getType() == Material.FISHING_ROD);
    }
}
