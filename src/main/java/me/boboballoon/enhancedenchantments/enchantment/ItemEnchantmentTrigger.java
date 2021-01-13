package me.boboballoon.enhancedenchantments.enchantment;

/**
 * Represents all valid enchantment triggers for item related enchantments
 */
public enum ItemEnchantmentTrigger implements ItemTrigger {
    /**
     * Will fire when the itemstack is used to break a block, event will return BlockBreakEvent
     * @see org.bukkit.event.block.BlockBreakEvent
     */
    ON_BLOCK_BREAK,
    /**
     * Will fire when the itemstack is used to hurt another entity, event will return EntityDamageByEntityEvent
     * @see org.bukkit.event.entity.EntityDamageByEntityEvent
     */
    ON_DAMAGE_DEALT,
    /**
     * Will fire when the itemstack is used to kill another entity, event will return EntityDeathEvent
     * @see org.bukkit.event.entity.EntityDeathEvent
     */
    ON_ENTITY_KILLED
}
