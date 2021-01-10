package me.boboballoon.enhancedenchantments.ui;

import me.boboballoon.enhancedenchantments.ui.anvil.AnvilMainUI;
import me.boboballoon.enhancedenchantments.ui.anvil.AnvilUI;
import me.boboballoon.enhancedenchantments.ui.enchantingtable.EnchantingTableUI;
import org.bukkit.entity.Player;

public final class UIManager {
    private final EnchantingTableUI enchantingTableUI;
    private final AnvilMainUI anvilMainUI;

    public UIManager() {
        this.enchantingTableUI = new EnchantingTableUI();
        this.anvilMainUI = new AnvilMainUI();
    }

    public void openEnchantingTable(Player player) {
        player.closeInventory();
        player.openInventory(this.enchantingTableUI.getInventory());
    }

    public void openAnvil(Player player) {
        new AnvilUI(this.anvilMainUI.getInventory(), player);
    }
}
