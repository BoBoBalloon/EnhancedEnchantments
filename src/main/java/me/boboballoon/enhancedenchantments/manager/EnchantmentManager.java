package me.boboballoon.enhancedenchantments.manager;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.enchantment.Enchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/**
 * A class that deals with registering enchantments
 */
public final class EnchantmentManager {
    private final Map<String, Enchantment> enchantments;

    public EnchantmentManager() {
        this.enchantments = new HashMap<>();
    }

    /**
     * Registers enchantments to be loaded/functional in-game
     *
     * @param enchantments all of the enchantments you wish to register
     */
    public void registerEnchantments(Enchantment... enchantments) {
        for (Enchantment enchantment : enchantments) {
            if (this.enchantments.containsKey(enchantment.getName())) {
                EnhancedEnchantments.getInstance().getLogger().log(Level.SEVERE, "An enchantment with the name " + enchantment.getName() + " already exists, registering has been cancelled!");
                return;
            }

            this.enchantments.put(enchantment.getName(), enchantment);
        }
    }

    /**
     * Unregisters enchantments that are loaded/functional in-game
     *
     * @param enchantments all of the enchantments you wish to unregister
     */
    public void unregisterEnchantments(Enchantment... enchantments) {
        for (Enchantment enchantment : enchantments) {
            this.enchantments.remove(enchantment.getName());
        }
    }

    /**
     * Returns the enchantment that has the name you put
     *
     * @param name the name of the enchantment
     * @return the enchantment, null if enchantment is not registered or does not exist
     */
    public Enchantment getEnchantment(String name) {
        return this.enchantments.get(name);
    }

    /**
     * Returns all registered enchantments from the specified tier
     *
     * @param tier the specified tier
     * @return all registered enchantments from the specified tier
     */
    public Set<Enchantment> getEnchantments(EnchantmentTier tier) {
        Set<Enchantment> enchantments = new HashSet<>();
        for (Enchantment enchantment : this.enchantments.values()) {
            if (enchantment.getTier() == tier) {
                enchantments.add(enchantment);
            }
        }
        return enchantments;
    }

    /**
     * Returns a copy set of all active enchantments
     *
     * @return a copy set of all active enchantments
     */
    public Set<Enchantment> getEnchantments() {
        return new HashSet<>(this.enchantments.values());
    }
}
