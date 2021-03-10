package me.boboballoon.enhancedenchantments.manager;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.enchantment.EnchantedBook;
import me.boboballoon.enhancedenchantments.enchantment.Enchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentHolder;
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

        return item.getItemMeta().getPersistentDataContainer().has(EnchantmentHolder.KEY, PersistentDataType.STRING);
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

        return item.getItemMeta().getPersistentDataContainer().has(EnchantedBook.KEY, PersistentDataType.STRING);
    }

    /**
     * Used to get enchantments off of an item
     *
     * @param item the item which you wish to check
     * @return the enchantment holder, will create a new one if nothing is present
     */
    public static EnchantmentHolder getEnchantmentHolder(ItemStack item) {
        if (!EnchantmentUtil.isEnchanted(item)) {
            return EnchantmentUtil.buildHolder(item);
        }

        EnchantmentHolder holder = EnchantmentHolder.fromString(item.getItemMeta().getPersistentDataContainer().get(EnchantmentHolder.KEY, PersistentDataType.STRING));

        if (holder.isEmpty()) {
            ItemMeta meta = item.getItemMeta();
            for (org.bukkit.enchantments.Enchantment enchantment : meta.getEnchants().keySet()) {
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

    /**
     * Returns an enchanted book object based on an itemstack
     *
     * @param item the enchanted book itemstack
     * @return an enchanted book object based on said itemstack, null if itemstack is not an enchanted book
     */
    public static EnchantedBook getEnchantedBook(ItemStack item) {
        if (!EnchantmentUtil.isEnchantedBook(item)) {
            return null;
        }

        String[] split = item.getItemMeta().getPersistentDataContainer().get(EnchantedBook.KEY, PersistentDataType.STRING).split("-");

        Enchantment enchantment = EnhancedEnchantments.getInstance().getEnchantmentManager().getEnchantment(split[0]);

        if (enchantment == null) {
            item.setAmount(0); //delete book
            return null;
        }

        int level;
        try {
            level = Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        return new EnchantedBook(enchantment, level);
    }

    private static EnchantmentHolder buildHolder(ItemStack item) {
        EnchantmentHolder holder = new EnchantmentHolder();
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(EnchantmentHolder.KEY, PersistentDataType.STRING, holder.toString());
        item.setItemMeta(meta);
        return holder;
    }
}
