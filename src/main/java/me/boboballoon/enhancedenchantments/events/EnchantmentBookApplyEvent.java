package me.boboballoon.enhancedenchantments.events;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EnchantmentBookApplyEvent extends Event implements Cancellable {
    private final ActiveEnchantment enchantment;
    private boolean cancelled;

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public EnchantmentBookApplyEvent(ActiveEnchantment enchantment) {
        this.enchantment = enchantment;
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
