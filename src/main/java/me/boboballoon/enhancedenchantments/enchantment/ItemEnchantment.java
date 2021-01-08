package me.boboballoon.enhancedenchantments.enchantment;

public abstract class ItemEnchantment extends Enchantment {
    public ItemEnchantment(String name, int maxLevel, EnchantmentTier tier, ItemEnchantmentTrigger trigger) {
        super(name, maxLevel, tier, trigger);
    }
}
