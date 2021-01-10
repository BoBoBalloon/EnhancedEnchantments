package me.boboballoon.enhancedenchantments.ui.enchantingtable;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.ui.ItemUtil;
import me.boboballoon.enhancedenchantments.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class EnchantingTableUI implements InventoryHolder {
    private final Inventory inventory;

    public static final NamespacedKey KEY = new NamespacedKey(EnhancedEnchantments.getInstance(), "tier_book_menu_item");

    public EnchantingTableUI() {
        this.inventory = Bukkit.createInventory(this, 27, TextUtil.format("&r&dEnchanting Table"));

        ItemStack pink = ItemUtil.background(Material.PINK_STAINED_GLASS_PANE);
        ItemStack magenta = ItemUtil.background(Material.MAGENTA_STAINED_GLASS_PANE);

        int[] pinkSlots = new int[]{2, 6, 10, 12, 14, 16, 20, 24};
        for (int slot : pinkSlots) {
            this.inventory.setItem(slot, pink);
        }

        int[] magentaSlots = new int[]{1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};
        for (int slot : magentaSlots) {
            this.inventory.setItem(slot, magenta);
        }

        this.inventory.setItem(13, ItemUtil.background(Material.PURPLE_STAINED_GLASS_PANE));

        List<ItemStack> books = this.buildBooks();

        int[] bookSlots = new int[]{0, 4, 8, 18, 22, 26};
        for (int i = 0; i < 6; i++) {
            this.inventory.setItem(bookSlots[i], books.get(i));
        }
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    private List<ItemStack> buildBooks() {
        List<ItemStack> books = new ArrayList<>();

        for (EnchantmentTier tier : EnchantmentTier.values()) {
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
            ItemMeta meta = item.getItemMeta();

            String name = tier.name().charAt(0) + tier.name().substring(1).toLowerCase();

            meta.setDisplayName(tier.getColor() + name);

            meta.setLore(Collections.singletonList(TextUtil.format("&r&fExperience Cost: " + tier.getLevels())));

            meta.getPersistentDataContainer().set(KEY, PersistentDataType.STRING, tier.name());

            item.setItemMeta(meta);
            books.add(item);
        }

        return books;
    }
}
