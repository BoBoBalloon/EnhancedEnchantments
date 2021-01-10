package me.boboballoon.enhancedenchantments.ui;

import me.boboballoon.enhancedenchantments.utils.TextUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {
    public static ItemStack background() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(TextUtil.format("&r"));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack background(Material material) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(TextUtil.format("&r"));

        item.setItemMeta(meta);
        return item;
    }
}
