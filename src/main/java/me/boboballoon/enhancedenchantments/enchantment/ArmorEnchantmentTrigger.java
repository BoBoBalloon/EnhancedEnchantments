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
    /**
     * Will fire once every second, event will return PlayerNullEvent
     * @see me.boboballoon.enhancedenchantments.events.PlayerNullEvent
     * @deprecated replaced by {@link UniversalEnchantmentTrigger#EVERY_SECOND}
     */
    EVERY_SECOND
    //REMOVE SOON ^^^
}
