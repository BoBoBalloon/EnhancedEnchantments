package me.boboballoon.enhancedenchantments.enchantment;

/**
 * Represents all valid enchantment triggers for bow related enchantments
 */
public enum BowEnchantmentTrigger implements BowTrigger {
    /**
     * Will fire when the bow fires an arrow, event will return EntityShootBowEvent
     * @see org.bukkit.event.entity.EntityShootBowEvent
     */
    ON_ARROW_FIRED,
    /**
     * Will fire when an arrow hits an entity, event will return ProjectileHitEvent
     * @see org.bukkit.event.entity.ProjectileHitEvent
     */
    ON_ARROW_HIT
}
