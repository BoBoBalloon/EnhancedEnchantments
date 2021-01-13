package me.boboballoon.enhancedenchantments.enchantment;

/**
 * Represents all valid enchantment triggers for armor related enchantments
 */
public enum ArmorEnchantmentTrigger implements ArmorTrigger {
    /**
     * Will fire when the player takes damage, event will return EntityDamageEvent
     * @see org.bukkit.event.entity.EntityDamageEvent
     */
    ON_DAMAGE_TAKEN,
}
