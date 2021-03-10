package me.boboballoon.enhancedenchantments.listeners;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantedBook;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentHolder;
import me.boboballoon.enhancedenchantments.events.EnchantmentBookApplyEvent;
import me.boboballoon.enhancedenchantments.manager.EnchantmentUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EnchantmentBookApplyListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (!(event.getClickedInventory() instanceof PlayerInventory)) {
            return;
        }

        if (event.getAction() != InventoryAction.SWAP_WITH_CURSOR) {
            return;
        }

        ItemStack clicked = event.getCurrentItem();
        ItemStack cursor = event.getCursor();

        if (cursor == null) {
            return;
        }

        EnchantedBook enchantedBook = EnchantedBook.fromBook(cursor);

        if (enchantedBook == null) {
            return;
        }

        EnchantmentHolder holder = EnchantmentUtil.getEnchantmentHolder(clicked);

        event.setCancelled(true);

        if (holder == null) {
            return;
        }

        if (!enchantedBook.getEnchantment().canEnchant(clicked)) {
            return;
        }

        if (holder.hasEnchantment(enchantedBook.getEnchantment()) && holder.getEnchantment(enchantedBook.getEnchantment()).getLevel() >= enchantedBook.getLevel()) {
            return;
        }

        EnchantmentBookApplyEvent applyEvent = new EnchantmentBookApplyEvent(new ActiveEnchantment(enchantedBook.getEnchantment(), enchantedBook.getLevel()), holder);
        Bukkit.getPluginManager().callEvent(applyEvent);

        if (applyEvent.isCancelled()) {
            return;
        }

        holder.addEnchantment(applyEvent.getEnchantment(), clicked);

        event.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
    }

}
