package me.boboballoon.enhancedenchantments.ui.anvil;

import me.boboballoon.enhancedenchantments.enchantment.EnchantedBook;
import me.boboballoon.enhancedenchantments.enchantment.Enchantment;
import me.boboballoon.enhancedenchantments.manager.EnchantmentUtil;
import me.boboballoon.enhancedenchantments.ui.ItemUtil;
import me.boboballoon.enhancedenchantments.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class AnvilUI implements InventoryHolder {
    private final Inventory inventory;
    private final Player player;

    public AnvilUI(Inventory inventory, Player player) {
        this.inventory = Bukkit.createInventory(this, 45, TextUtil.format("&r&7Anvil"));

        this.inventory.setContents(inventory.getContents());

        this.player = player;

        player.closeInventory();
        player.openInventory(this.inventory);
    }

    public void reloadInventory() {
        ItemStack first = this.inventory.getItem(29);
        ItemStack second = this.inventory.getItem(33);

        ItemStack red = ItemUtil.background(Material.RED_STAINED_GLASS_PANE);
        ItemStack green = ItemUtil.background(Material.GREEN_STAINED_GLASS_PANE);

        if (EnchantmentUtil.isEnchantedBook(first)) {
            this.inventory.setItem(21, green);
        } else {
            this.inventory.setItem(21, red);
        }

        if (EnchantmentUtil.isEnchantedBook(second)) {
            this.inventory.setItem(23, green);
        } else {
            this.inventory.setItem(23, red);
        }

        this.inventory.setItem(13, ItemUtil.background(Material.BARRIER));

        if (first == null || second == null) {
            return;
        }

        EnchantedBook firstBook = EnchantmentUtil.getEnchantedBook(first);
        EnchantedBook secondBook = EnchantmentUtil.getEnchantedBook(second);

        if (firstBook == null || secondBook == null) {
            return;
        }

        if (!firstBook.getEnchantment().getName().equals(secondBook.getEnchantment().getName())) {
            return;
        }

        Enchantment enchantment = firstBook.getEnchantment();

        if (firstBook.getLevel() != secondBook.getLevel()) {
            return;
        }

        int level = firstBook.getLevel() + 1;

        if (level > enchantment.getMaxLevel()) {
            return;
        }

        EnchantedBook book = new EnchantedBook(enchantment, level);

        this.inventory.setItem(13, book.getBook());
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public Player getPlayer() {
        return this.player;
    }
}
