package me.boboballoon.enhancedenchantments.ui.anvil;

import me.boboballoon.enhancedenchantments.ui.ItemUtil;
import me.boboballoon.enhancedenchantments.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class AnvilMainUI implements InventoryHolder {
    private final Inventory inventory;

    public AnvilMainUI() {
        this.inventory = Bukkit.createInventory(this, 45, TextUtil.format("&r&7Anvil"));

        ItemStack background = ItemUtil.background();
        int[] backgroundSlots = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37,
        38, 39, 40, 41, 42, 43, 44};
        for (int slot : backgroundSlots) {
            this.inventory.setItem(slot, background);
        }

        ItemStack gray = ItemUtil.background(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        int[] graySlots = new int[]{10, 11, 12, 14, 15, 16, 19, 20, 22, 24, 25, 28, 30, 31, 32, 34};
        for (int slot : graySlots) {
            this.inventory.setItem(slot, gray);
        }

        ItemStack badItem = ItemUtil.background(Material.RED_STAINED_GLASS_PANE);

        this.inventory.setItem(21, badItem);
        this.inventory.setItem(23, badItem);

        this.inventory.setItem(13, ItemUtil.background(Material.BARRIER));
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }
}
