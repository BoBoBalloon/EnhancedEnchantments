package me.boboballoon.enhancedenchantments.enchantment;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.utils.TextUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

/**
 * Class that represents an enchantment book
 */
public class EnchantedBook {
    private final Enchantment enchantment;
    private final int level;
    private final ItemStack book;

    public static final NamespacedKey KEY = new NamespacedKey(EnhancedEnchantments.getInstance(), "enchanted_book_nbt");

    public EnchantedBook(Enchantment enchantment, int level) {
        this.enchantment = enchantment;
        this.level = Math.min(level, enchantment.getMaxLevel());
        this.book = this.buildBook();
    }

    /**
     * Returns the enchantment that this book holds
     *
     * @return the enchantment that this book holds
     */
    public Enchantment getEnchantment() {
        return this.enchantment;
    }

    /**
     * Returns the enchantment level that this book holds
     *
     * @return the enchantment level that this book holds
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Returns the itemstack the correlates with this book
     *
     * @return the itemstack the correlates with this book
     */
    public ItemStack getBook() {
        return this.book;
    }

    private ItemStack buildBook() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(TextUtil.format("&r" + this.enchantment.getTier().getColor() + this.enchantment.getName() + "&r&7&l - " + this.level));

        List<String> lore = this.enchantment.getDescription();
        for (int i = 0; i < lore.size(); i++) {
            String line = lore.get(i);
            line = this.getEnchantment().getTier().getColor() + line;
            lore.set(i, line);
        }

        lore.add("");

        lore.add(ChatColor.RED + "Applicable to " + this.enchantment.applicableTo());

        meta.setLore(lore);

        meta.getPersistentDataContainer().set(KEY, PersistentDataType.STRING, this.enchantment.getName() + "-" + this.level);

        item.setItemMeta(meta);
        return item;
    }
}
