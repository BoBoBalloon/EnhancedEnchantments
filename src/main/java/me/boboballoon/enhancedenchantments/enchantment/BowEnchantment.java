package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Superclass of any enchantment having to do with any type of bow
 */
public abstract class BowEnchantment extends Enchantment {
    public BowEnchantment(String name, int maxLevel, EnchantmentTier tier, BowTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }

    @Override
    public String applicableTo() {
        return "bows/crossbows";
    }

    @Override
    public boolean canEnchant(ItemStack item) {
        return (item.getType() == Material.BOW || item.getType() == Material.CROSSBOW);
    }
}
