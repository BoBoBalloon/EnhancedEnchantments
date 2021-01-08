package me.boboballoon.enhancedenchantments;

import me.boboballoon.enhancedenchantments.commands.GetEnchantmentCommand;
import me.boboballoon.enhancedenchantments.commands.GetEnchantmentCompleter;
import me.boboballoon.enhancedenchantments.enchantments.InstantKill;
import me.boboballoon.enhancedenchantments.enchantments.UpUpAndAway;
import me.boboballoon.enhancedenchantments.listeners.EnchantmentBookApplyListener;
import me.boboballoon.enhancedenchantments.manager.EnchantmentManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnhancedEnchantments extends JavaPlugin {
    private static EnhancedEnchantments instance;

    private final EnchantmentManager enchantmentManager = new EnchantmentManager();

    /*
    TODO:
    1. add descriptions to all enchantments
    2. make api events that other devs can listen for
    3. make enchanting table ui
    4. disable all normal enchantments
     */

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getPluginCommand("getenchantment").setExecutor(new GetEnchantmentCommand());
        Bukkit.getPluginCommand("getenchantment").setTabCompleter(new GetEnchantmentCompleter());

        this.registerListeners(this.enchantmentManager, new EnchantmentBookApplyListener());

        this.enchantmentManager.registerEnchantments(new InstantKill(), new UpUpAndAway()); //add enchantments
    }

    /**
     * Returns plugin instance
     *
     * @return plugin instance
     */
    public static EnhancedEnchantments getInstance() {
        return instance;
    }

    /**
     * Returns enchantment manager
     *
     * @return enchantment manager
     */
    public EnchantmentManager getEnchantmentManager() {
        return this.enchantmentManager;
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }
}
