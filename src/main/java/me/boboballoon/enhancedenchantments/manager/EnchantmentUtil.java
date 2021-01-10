package me.boboballoon.enhancedenchantments.manager;

import me.boboballoon.enhancedenchantments.enchantment.EnchantmentHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

/**
 * A class that has util methods to help retrieve enchantments from itemstacks
 */
public class EnchantmentUtil {

    /**
     * Check if an item is enchanted
     *
     * @param item the item which you wish to check
     * @return true if the item does have an enchantment otherwise false
     */
    public static boolean isEnchanted(ItemStack item) {
        if (!item.hasItemMeta()) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();

        return meta.getPersistentDataContainer().has(EnchantmentHolder.KEY, PersistentDataType.STRING);
    }

    /**
     * Used to get enchantments off of an item
     *
     * @param item the item which you wish to check
     * @return the enchantment holder, will create a new one if nothing is present
     */
    public static EnchantmentHolder getEnchantmentHolder(ItemStack item) {
        if (!isEnchanted(item)) {
            return buildHolder(item);
        }

        return EnchantmentHolder.fromString(item.getItemMeta().getPersistentDataContainer().get(EnchantmentHolder.KEY, PersistentDataType.STRING));
    }

    private static EnchantmentHolder buildHolder(ItemStack item) {
        EnchantmentHolder holder = new EnchantmentHolder();
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(EnchantmentHolder.KEY, PersistentDataType.STRING, holder.toString());
        item.setItemMeta(meta);
        return holder;
    }
}
