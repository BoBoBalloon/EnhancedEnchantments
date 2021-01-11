package me.boboballoon.enhancedenchantments.enchantment;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

/**
 * Superclass of any type of enchantment
 */
public abstract class Enchantment {
    /**
     * Name cannot contain the characters `-` or `:`
     */
    private final String name;
    private final int maxLevel;
    private final EnchantmentTier tier;
    private final EnchantmentTrigger trigger;
    private final List<String> description;

    /**
     * random object built into enchantment class so you don't have to initialize your own
     */
    protected final Random random = ThreadLocalRandom.current();

    public Enchantment(String name, int maxLevel, EnchantmentTier tier, EnchantmentTrigger trigger, List<String> description) {
        if (name.contains("-") || name.contains(":")) {
            EnhancedEnchantments.getInstance().getLogger().log(Level.SEVERE, "You cannot have an enchantment name contain the characters `-` or `:`");
            name = null;
        }
        this.name = name;
        this.maxLevel = maxLevel;
        this.tier = tier;
        this.trigger = trigger;
        this.description = description;
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
     * Returns a copy of the description of this enchantment
     *
     * @return a copy of the description of this enchantment
     */
    public List<String> getDescription() {
        return new ArrayList<>(this.description);
    }


    /**
     * Returns a string that describes what types of items the enchantment can be applied to
     * (e.g. "weapons and tools")
     *
     * @return a string that describes what types of items the enchantment can be applied to
     */
    public abstract String applicableTo();

    /**
     * Returns a boolean that is true when an item can be enchanted, false when the item cannot be
     *
     * @param item the item you wish to check
     * @return a boolean that is true when an item can be enchanted, false when the item cannot be
     */
    public abstract boolean canEnchant(ItemStack item);

    /**
     * Effect that the enchantment gives
     *
     * @param event the bukkit event that was fired for this effect
     * @param enchantment the active enchantment that triggered this method
     */
    public abstract void effect(Event event, ActiveEnchantment enchantment);
}
