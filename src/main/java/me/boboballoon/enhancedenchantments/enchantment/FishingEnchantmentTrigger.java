package me.boboballoon.enhancedenchantments.enchantment;

/**
 * Represents all valid enchantment triggers for fishing related enchantments
 */
public enum FishingEnchantmentTrigger implements FishingTrigger {
    /**
     * Will fire when the itemstack is used to fish, event will return PlayerFishEvent
     * @see org.bukkit.event.player.PlayerFishEvent
     */
    ON_FISH
}
