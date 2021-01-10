package me.boboballoon.enhancedenchantments.ui;

import me.boboballoon.enhancedenchantments.ui.enchantingtable.EnchantingTableUI;
import org.bukkit.entity.Player;

public final class UIManager {
    private final EnchantingTableUI enchantingTableUI;

    public UIManager() {
        this.enchantingTableUI = new EnchantingTableUI();
    }

    public void openEnchantingTable(Player player) {
        player.closeInventory();
        player.openInventory(this.enchantingTableUI.getInventory());
    }
}
