package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.ChatColor;

public enum EnchantmentTier {
    COMMON(1, ChatColor.WHITE),
    UNCOMMON(2, ChatColor.GREEN),
    RARE(3, ChatColor.BLUE),
    EPIC(4, ChatColor.DARK_PURPLE),
    LEGENDARY(5, ChatColor.GOLD),
    MYTHIC(6, ChatColor.LIGHT_PURPLE);

    private final int tier;
    private final ChatColor color;

    EnchantmentTier(int tier, ChatColor color) {
        this.tier = tier;
        this.color = color;
    }

    /**
     * Returns the tier number of the enchantment tier
     *
     * @return the tier number of the enchantment tier
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
}
