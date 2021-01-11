package me.boboballoon.enhancedenchantments.manager;

import me.boboballoon.enhancedenchantments.enchantment.EnchantedBook;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentHolder;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;

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
        if (item == null || !item.hasItemMeta()) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();

        return meta.getPersistentDataContainer().has(EnchantmentHolder.KEY, PersistentDataType.STRING);
    }

    /**
     * Check if an item is an enchanted book
     *
     * @param item the item which you wish to check
     * @return true if the item is an enchanted book otherwise false
     */
    public static boolean isEnchantedBook(ItemStack item) {
        if (item == null || !item.hasItemMeta()) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();

        return meta.getPersistentDataContainer().has(EnchantedBook.KEY, PersistentDataType.STRING);
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

        EnchantmentHolder holder = EnchantmentHolder.fromString(item.getItemMeta().getPersistentDataContainer().get(EnchantmentHolder.KEY, PersistentDataType.STRING));

        if (holder.isEmpty()) {
            ItemMeta meta = item.getItemMeta();
            for (Enchantment enchantment : meta.getEnchants().keySet()) {
                meta.removeEnchant(enchantment);
            }
            meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.setLore(Collections.emptyList());
            meta.getPersistentDataContainer().remove(EnchantmentHolder.KEY);
            item.setItemMeta(meta);
            return null;
        }

        holder.updateItem(item);
        return holder;
    }

    private static EnchantmentHolder buildHolder(ItemStack item) {
        EnchantmentHolder holder = new EnchantmentHolder();
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(EnchantmentHolder.KEY, PersistentDataType.STRING, holder.toString());
        item.setItemMeta(meta);
        return holder;
    }
}
