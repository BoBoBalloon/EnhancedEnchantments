package me.boboballoon.enhancedenchantments.ui.anvil;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.manager.EnchantmentUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class AnvilListener implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getInventory().getType() == InventoryType.ANVIL) {
            Bukkit.getScheduler().runTaskLater(EnhancedEnchantments.getInstance(), () -> EnhancedEnchantments.getInstance().getUiManager().openAnvil((Player) event.getPlayer()), 0L);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        if (!(inventory.getHolder() instanceof AnvilUI)) {
            return;
        }

        HumanEntity player = event.getPlayer();

        ItemStack first = inventory.getItem(29);
        ItemStack second = inventory.getItem(33);

        if (first != null) {
            player.getInventory().addItem(first);
        }

        if (second != null) {
            player.getInventory().addItem(second);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

        if (inventory == null || !(inventory.getHolder() instanceof AnvilUI)) {
            return;
        }

        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        AnvilUI anvil = (AnvilUI) inventory.getHolder();

        boolean check = this.check(inventory, event.getSlot());

        Bukkit.getScheduler().runTaskLater(EnhancedEnchantments.getInstance(), () -> {
            anvil.reloadInventory();
            player.updateInventory();
        }, 1L);

        if (event.getCurrentItem() == null || check) {
            return;
        }

        event.setCancelled(true);
    }

    private boolean check(Inventory inventory, int slot) {
        if (slot == 29 || slot == 33) {
            return true;
        }

        ItemStack output = inventory.getItem(13);

        if (output == null) {
            EnhancedEnchantments.getInstance().getLogger().log(Level.WARNING, "something went very wrong : EnhancedEnchantments : AnvilListener.java");
            return true; //because false would make it cancel the event
        }

        if (slot == 13 && EnchantmentUtil.isEnchantedBook(output)) {
            inventory.setItem(29, null);
            inventory.setItem(33, null);
            return true;
        }

        return false;
    }
}
