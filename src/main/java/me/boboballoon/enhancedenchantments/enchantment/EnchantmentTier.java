package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.ChatColor;

/**
 * Represents a tier of an enchantment
 */
public enum EnchantmentTier {
    COMMON(1, ChatColor.WHITE, 5),
    UNCOMMON(2, ChatColor.GREEN, 10),
    RARE(3, ChatColor.BLUE, 20),
    EPIC(4, ChatColor.DARK_PURPLE, 35),
    LEGENDARY(5, ChatColor.GOLD, 50),
    MYTHIC(6, ChatColor.LIGHT_PURPLE, 100);

    private final int tier;
    private final ChatColor color;
    private final int levels;

    EnchantmentTier(int tier, ChatColor color, int levels) {
        this.tier = tier;
        this.color = color;
        this.levels = levels;
    }

    /**
     * Returns the tier number of the enchantment tier (magic number)
     *
     * @return the tier number of the enchantment tier (magic number)
     */
    public int getTier() {
        return this.tier;
    }

    /**
     * Returns the chat color of the enchantment tier
     *
     * @return chat color of the enchantment tier
     */
    public ChatColor getColor() {
        return this.color;
    }

    /**
     * Returns the amount of exp levels required to get an enchantment of this tier
     *
     * @return the amount of exp levels required to get an enchantment of this tier
     */
    public int getLevels() {
        return this.levels;
    }
}
