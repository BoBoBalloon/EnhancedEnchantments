package me.boboballoon.enhancedenchantments.events;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentHolder;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * An event that's fired every time a player tries to apply an enchantment book to an item
 */
public class EnchantmentBookApplyEvent extends Event implements Cancellable {
    private final ActiveEnchantment enchantment;
    private final EnchantmentHolder holder;
    private boolean cancelled;

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public EnchantmentBookApplyEvent(ActiveEnchantment enchantment, EnchantmentHolder holder) {
        this.enchantment = enchantment;
        this.holder = holder;
        this.cancelled = false;
    }

    /**
     * Returns the enchantment about to be applied
     *
     * @return the enchantment about to be applied
     */
    public ActiveEnchantment getEnchantment() {
        return this.enchantment;
    }

    /**
     * Returns the holder that the active enchantment is about to be added to
     *
     * @return the holder that the active enchantment is about to be added to
     */
    public EnchantmentHolder getHolder() {
        return this.holder;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
}
