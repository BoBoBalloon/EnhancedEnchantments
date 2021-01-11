package me.boboballoon.enhancedenchantments;

import me.boboballoon.enhancedenchantments.commands.GetEnchantmentCommand;
import me.boboballoon.enhancedenchantments.commands.GetEnchantmentCompleter;
import me.boboballoon.enhancedenchantments.enchantments.*;
import me.boboballoon.enhancedenchantments.listeners.EnchantmentBookApplyListener;
import me.boboballoon.enhancedenchantments.listeners.GrindstoneOpenListener;
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

    private EnchantmentManager enchantmentManager;
    private UIManager uiManager;

    @Override
    public void onEnable() {
        instance = this;
        enchantmentManager = new EnchantmentManager();
        uiManager = new UIManager();

        Bukkit.getPluginCommand("getenchantment").setExecutor(new GetEnchantmentCommand());
        Bukkit.getPluginCommand("getenchantment").setTabCompleter(new GetEnchantmentCompleter());

        this.registerListeners(this.enchantmentManager, new EnchantmentBookApplyListener(), new EnchantingTableListener(),
                new AnvilListener(), new RemoveVanillaEnchantmentListener(), new GrindstoneOpenListener());

        this.enchantmentManager.registerEnchantments(new AllSeeingEyeEnchant(), new AntiGravityEnchant(), new DeterminedEnchant(),
                new DevourEnchant(), new InquiringEnchant(), new LifestealEnchantment(), new UntouchableEnchant(), new TelepathyEnchant(),
                new UnwaveringEnchant());
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
