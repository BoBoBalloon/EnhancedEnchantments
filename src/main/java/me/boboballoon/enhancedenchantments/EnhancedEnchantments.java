package me.boboballoon.enhancedenchantments;

import me.boboballoon.enhancedenchantments.commands.GetEnchantmentCommand;
import me.boboballoon.enhancedenchantments.commands.GetEnchantmentCompleter;
import me.boboballoon.enhancedenchantments.enchantments.InstantKill;
import me.boboballoon.enhancedenchantments.enchantments.UltraThorns;
import me.boboballoon.enhancedenchantments.enchantments.UpUpAndAway;
import me.boboballoon.enhancedenchantments.listeners.EnchantmentBookApplyListener;
import me.boboballoon.enhancedenchantments.listeners.RemoveVanillaEnchantmentListener;
import me.boboballoon.enhancedenchantments.manager.EnchantmentManager;
import me.boboballoon.enhancedenchantments.ui.UIManager;
import me.boboballoon.enhancedenchantments.ui.anvil.AnvilListener;
import me.boboballoon.enhancedenchantments.ui.enchantingtable.EnchantingTableListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnhancedEnchantments extends JavaPlugin {
    private static EnhancedEnchantments instance;

    private UIManager uiManager;
    private final EnchantmentManager enchantmentManager = new EnchantmentManager();

    @Override
    public void onEnable() {
        instance = this;
        uiManager = new UIManager();

        Bukkit.getPluginCommand("getenchantment").setExecutor(new GetEnchantmentCommand());
        Bukkit.getPluginCommand("getenchantment").setTabCompleter(new GetEnchantmentCompleter());

        this.registerListeners(this.enchantmentManager, new EnchantmentBookApplyListener(), new EnchantingTableListener(),
                new AnvilListener(), new RemoveVanillaEnchantmentListener());

        this.enchantmentManager.registerEnchantments(new InstantKill(), new UpUpAndAway(), new UltraThorns()); //add enchantments
    }


    public static EnhancedEnchantments getInstance() {
        return instance;
    }

    public EnchantmentManager getEnchantmentManager() {
        return this.enchantmentManager;
    }

    public UIManager getUiManager() {
        return this.uiManager;
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }
}
