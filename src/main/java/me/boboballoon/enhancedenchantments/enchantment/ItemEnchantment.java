package me.boboballoon.enhancedenchantments.enchantment;

import java.util.List;

public abstract class ItemEnchantment extends Enchantment {
    public ItemEnchantment(String name, int maxLevel, EnchantmentTier tier, EnchantmentTrigger trigger, List<String> description) {
        super(name, maxLevel, tier, trigger, description);
    }
}
