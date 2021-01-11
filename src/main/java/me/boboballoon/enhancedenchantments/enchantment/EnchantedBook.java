package me.boboballoon.enhancedenchantments.enchantment;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.utils.TextUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.logging.Level;

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
        if (level > enchantment.getMaxLevel()) {
            level = enchantment.getMaxLevel();
        } else if (level < 1) {
            level = 1;
        }
        this.level = level;
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

    /**
     * Returns an enchanted book object based on an itemstack
     *
     * @param item the enchanted book itemstack
     * @return an enchanted book object based on said itemstack, null if itemstack is not an enchanted book
     */
    public static EnchantedBook fromBook(ItemStack item) {
        if (!item.hasItemMeta()) {
            return null;
        }

        ItemMeta meta = item.getItemMeta();

        if (!meta.getPersistentDataContainer().has(KEY, PersistentDataType.STRING)) {
            return null;
        }

        String[] split = meta.getPersistentDataContainer().get(KEY, PersistentDataType.STRING).split("-");

        Enchantment enchantment = EnhancedEnchantments.getInstance().getEnchantmentManager().getEnchantment(split[0]);

        if (enchantment == null) {
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

        String applicable;
        if (this.enchantment instanceof HelmetEnchantment) {
            //helmet enchantment
            applicable = TextUtil.format("&r&cApplicable to helmets");
        } else if (this.enchantment instanceof ChestplateEnchantment) {
            //chestplate enchantment
            applicable = TextUtil.format("&r&cApplicable to chestplates");
        } else if (this.enchantment instanceof LeggingsEnchantment) {
            //leggings enchantment
            applicable = TextUtil.format("&r&cApplicable to leggings");
        } else if (this.enchantment instanceof BootsEnchantment) {
            //boots enchantment
            applicable = TextUtil.format("&r&cApplicable to boots");
        } else if (this.enchantment instanceof WeaponEnchantment) {
            //weapon enchantment
            applicable = TextUtil.format("&r&cApplicable to weapons");
        } else if (this.enchantment instanceof ToolEnchantment) {
            //tool enchantment
            applicable = TextUtil.format("&r&cApplicable to tools");
        } else if (this.enchantment instanceof ItemEnchantment) {
            //item enchantment
            applicable = TextUtil.format("&r&cApplicable to items");
        } else if (this.enchantment instanceof ArmorEnchantment) {
            //armor enchantment
            applicable = TextUtil.format("&r&cApplicable to armor");
        } else if (this.enchantment instanceof UniversalEnchantment) {
            //universal enchantment
            applicable = TextUtil.format("&r&cApplicable to weapons/tools/armor");
        } else {
            applicable = TextUtil.format("&r&4Unknown enchantment type (report to server owner)");
            EnhancedEnchantments.getInstance().getLogger().log(Level.WARNING, "The enchantment type of enchantment " + this.enchantment.getName() + " is unknown!");
        }

        lore.add(applicable);

        meta.setLore(lore);

        meta.getPersistentDataContainer().set(KEY, PersistentDataType.STRING, this.enchantment.getName() + "-" + this.level);

        item.setItemMeta(meta);
        return item;
    }
}
