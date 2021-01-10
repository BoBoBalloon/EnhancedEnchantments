package me.boboballoon.enhancedenchantments.api;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.manager.EnchantmentManager;

/**
 * Library of api methods to simplify getting internal information
 */
public class EnhancedEnchantmentsAPI {
    /**
     * Returns the active instance of the enchantment manager
     *
     * @return the active instance of the enchantment manager
     */
    public static EnchantmentManager getEnchantmentManager() {
        return EnhancedEnchantments.getInstance().getEnchantmentManager();
    }
}
