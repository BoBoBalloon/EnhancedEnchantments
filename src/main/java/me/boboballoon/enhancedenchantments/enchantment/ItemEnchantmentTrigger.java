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
     * Will fire when the itemstack is used and loses durability as a result, event will return PlayerItemDamageEvent
     * @see org.bukkit.event.player.PlayerItemDamageEvent
     * @deprecated replaced by {@link UniversalEnchantmentTrigger#ON_DURABILITY_LOSS}
     */
    ON_DURABILITY_LOSS,
    //REMOVE SOON ^^^
    /**
     * Will fire when the itemstack breaks, event will return PlayerItemBreakEvent
     * @see org.bukkit.event.player.PlayerItemBreakEvent
     * @deprecated replaced by {@link UniversalEnchantmentTrigger#ON_ITEM_BREAK}
     */
    ON_ITEM_BREAK,
    //REMOVE SOON ^^^
    /**
     * Will fire when the itemstack is used to hurt another entity, event will return EntityDamageByEntityEvent
     * @see org.bukkit.event.entity.EntityDamageByEntityEvent
     */
    ON_DAMAGE_DEALT,
    /**
     * Will fire when the itemstack is used to kill another entity, event will return EntityDeathEvent
     * @see org.bukkit.event.entity.EntityDeathEvent
     */
    ON_ENTITY_KILLED,
    /**
     * Will fire once every second, event will return PlayerNullEvent
     * @see me.boboballoon.enhancedenchantments.events.PlayerNullEvent
     */
    EVERY_SECOND
}
