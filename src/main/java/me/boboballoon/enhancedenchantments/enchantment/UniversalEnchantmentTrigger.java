package me.boboballoon.enhancedenchantments.enchantment;

/**
 * Represents all valid enchantment triggers universally
 */
public enum UniversalEnchantmentTrigger implements ArmorTrigger, ItemTrigger {
    /**
     * Will fire when the itemstack is used and loses durability as a result, event will return PlayerItemDamageEvent
     * @see org.bukkit.event.player.PlayerItemDamageEvent
     */
    ON_DURABILITY_LOSS,
    /**
     * Will fire when the itemstack breaks, event will return PlayerItemBreakEvent
     * @see org.bukkit.event.player.PlayerItemBreakEvent
     */
    ON_ITEM_BREAK,
    /**
     * Will fire once every second, event will return PlayerNullEvent
     * @see me.boboballoon.enhancedenchantments.events.PlayerNullEvent
     */
    EVERY_SECOND
}
