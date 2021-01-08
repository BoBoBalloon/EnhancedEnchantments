package me.boboballoon.enhancedenchantments.enchantment;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Enchantment {
    private final String name;
    private final int maxLevel;
    private final EnchantmentTier tier;
    private final EnchantmentTrigger trigger;

    /**
     * random object built into enchantment class so you don't have to initialize your own
     */
    protected final Random random = ThreadLocalRandom.current();

    public Enchantment(String name, int maxLevel, EnchantmentTier tier, EnchantmentTrigger trigger) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.tier = tier;
        this.trigger = trigger;
    }

    /**
     * Returns the name of the enchantment
     *
     * @return name of the enchantment
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the maximum level of the enchantment
     *
     * @return maximum level of the enchantment
     */
    public int getMaxLevel() {
        return this.maxLevel;
    }

    /**
     * Returns the tier of the enchantment
     *
     * @return tier of the enchantment
     */
    public EnchantmentTier getTier() {
        return this.tier;
    }

    /**
     * Returns the trigger of the enchantment
     *
     * @return trigger of the enchantment
     */
    public EnchantmentTrigger getTrigger() {
        return this.trigger;
    }

    /**
     * Returns a boolean that is true when an item can be enchanted, false when the item cannot be
     *
     * @param item the item you wish to check
     * @return a boolean that is true when an item can be enchanted, false when the item cannot be
     */
    public abstract boolean canEnchant(ItemStack item);

    /**
     * Effect that the enchantment gives
     */
    public abstract void effect(Event event, ActiveEnchantment enchantment);
}
