package me.boboballoon.enhancedenchantments.listeners;

import me.boboballoon.enhancedenchantments.enchantment.EnchantedBook;
import me.boboballoon.enhancedenchantments.manager.EnchantmentUtil;
import org.bukkit.Material;
import org.bukkit.block.Container;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class RemoveVanillaEnchantmentListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.removeEnchantments(event.getPlayer().getInventory());
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getHolder() instanceof Container) {
            this.removeEnchantments(inventory);
        }
    }

    private void removeEnchantments(Inventory inventory) {
        for (ItemStack item : inventory.getContents()) {
            if (item == null) {
                continue;
            }

            if (!item.hasItemMeta()) {
                continue;
            }

            if (EnchantmentUtil.isEnchanted(item)) {
                continue;
            }

            if (item.getType() == Material.ENCHANTED_BOOK &&
                    !item.getItemMeta().getPersistentDataContainer().has(EnchantedBook.KEY, PersistentDataType.STRING)) {
                item.setAmount(0);
                continue;
            }

            if (item.getItemMeta().hasEnchants()) {
                ItemMeta meta = item.getItemMeta();
                for (Enchantment enchantment : meta.getEnchants().keySet()) {
                    meta.removeEnchant(enchantment);
                }
                item.setItemMeta(meta);
            }
        }
    }
}
