package me.boboballoon.enhancedenchantments.ui.enchantingtable;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.enchantment.EnchantedBook;
import me.boboballoon.enhancedenchantments.enchantment.Enchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EnchantingTableListener implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getInventory().getType() == InventoryType.ENCHANTING) {
            Bukkit.getScheduler().runTaskLater(EnhancedEnchantments.getInstance(), () -> EnhancedEnchantments.getInstance().getUiManager().openEnchantingTable((Player) event.getPlayer()), 0L);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

        if (inventory == null || !(inventory.getHolder() instanceof EnchantingTableUI)) {
            return;
        }

        ItemStack item = event.getCurrentItem();

        event.setCancelled(true);

        if (!item.getItemMeta().getPersistentDataContainer().has(EnchantingTableUI.KEY, PersistentDataType.STRING)) {
            return;
        }

        EnchantmentTier tier = EnchantmentTier.valueOf(item.getItemMeta().getPersistentDataContainer().get(EnchantingTableUI.KEY, PersistentDataType.STRING));

        Player player = (Player) event.getWhoClicked();

        if (player.getLevel() < tier.getLevels()) {
            player.sendMessage(TextUtil.format("&r&cYou do not have enough levels to obtain this enchantment!"));
            return;
        }

        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(TextUtil.format("&r&cYou do not have enough space in your inventory to obtain this enchantment!"));
            return;
        }

        List<Enchantment> enchantments = new ArrayList<>(EnhancedEnchantments.getInstance().getEnchantmentManager().getEnchantments(tier));
        Random random = ThreadLocalRandom.current();

        int index = random.nextInt(enchantments.size());

        Enchantment enchantment = enchantments.get(index);

        EnchantedBook book = new EnchantedBook(enchantment, 1);

        player.giveExpLevels(-tier.getLevels());

        player.getInventory().addItem(book.getBook());
    }
}
