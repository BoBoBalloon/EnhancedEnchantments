package me.boboballoon.enhancedenchantments.enchantment;

/**
 * Represents all valid enchantment triggers for armor related enchantments
 */
public enum ArmorEnchantmentTrigger implements ArmorTrigger {
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
     * Will fire when the player takes damage, event will return EntityDamageEvent
     * @see org.bukkit.event.entity.EntityDamageEvent
     */
    ON_DAMAGE_TAKEN,
    /**
     * Will fire once every second, event will return PlayerNullEvent
     * @see me.boboballoon.enhancedenchantments.events.PlayerNullEvent
     */
    EVERY_SECOND
}
