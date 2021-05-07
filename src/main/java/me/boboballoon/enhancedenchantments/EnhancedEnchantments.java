package me.boboballoon.enhancedenchantments;

import me.boboballoon.enhancedenchantments.commands.GetEnchantmentCommand;
import me.boboballoon.enhancedenchantments.enchantments.UnwaveringEnchant;
import me.boboballoon.enhancedenchantments.enchantments.armor.boots.AntigravityEnchant;
import me.boboballoon.enhancedenchantments.enchantments.armor.chestplate.UntouchableEnchant;
import me.boboballoon.enhancedenchantments.enchantments.armor.helmet.AllSeeingEyeEnchant;
import me.boboballoon.enhancedenchantments.enchantments.armor.leggings.AdrenalineEnchant;
import me.boboballoon.enhancedenchantments.enchantments.items.tools.DeterminedEnchant;
import me.boboballoon.enhancedenchantments.enchantments.items.tools.TelepathyEnchant;
import me.boboballoon.enhancedenchantments.enchantments.items.tools.farming.BlessingOfNatureEnchant;
import me.boboballoon.enhancedenchantments.enchantments.items.tools.fishing.CallOfTheSeaEnchant;
import me.boboballoon.enhancedenchantments.enchantments.items.tools.fishing.SeasBountyEnchant;
import me.boboballoon.enhancedenchantments.enchantments.items.weapons.DevourEnchant;
import me.boboballoon.enhancedenchantments.enchantments.items.weapons.PenetrationEnchant;
import me.boboballoon.enhancedenchantments.enchantments.items.weapons.bows.ExplosiveEnchant;
import me.boboballoon.enhancedenchantments.enchantments.items.weapons.bows.InfinityEnchant;
import me.boboballoon.enhancedenchantments.enchantments.items.weapons.swords.*;
import me.boboballoon.enhancedenchantments.listeners.EnchantmentBookApplyListener;
import me.boboballoon.enhancedenchantments.listeners.EnchantmentEffectListener;
import me.boboballoon.enhancedenchantments.listeners.GrindstoneOpenListener;
import me.boboballoon.enhancedenchantments.listeners.RemoveVanillaEnchantmentListener;
import me.boboballoon.enhancedenchantments.manager.EnchantmentManager;
import me.boboballoon.enhancedenchantments.ui.UIManager;
import me.boboballoon.enhancedenchantments.ui.anvil.AnvilListener;
import me.boboballoon.enhancedenchantments.ui.enchantingtable.EnchantingTableListener;
import me.boboballoon.enhancedenchantments.utils.DummyEnchantment;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnhancedEnchantments extends JavaPlugin {
    private static EnhancedEnchantments instance;

    private EnchantmentManager enchantmentManager;
    private UIManager uiManager;

    @Override
    public void onEnable() {
        instance = this;
        this.enchantmentManager = new EnchantmentManager();
        this.uiManager = new UIManager();

        GetEnchantmentCommand.register();

        DummyEnchantment.start();

        this.registerListeners(new EnchantmentEffectListener(), new EnchantmentBookApplyListener(), new EnchantingTableListener(),
                new AnvilListener(), new RemoveVanillaEnchantmentListener(), new GrindstoneOpenListener());

        this.enchantmentManager.registerEnchantments(new AllSeeingEyeEnchant(), new AntigravityEnchant(), new DeterminedEnchant(),
                new DevourEnchant(), new InquiringEnchant(), new LifestealEnchantment(), new UntouchableEnchant(), new TelepathyEnchant(),
                new UnwaveringEnchant(), new PenetrationEnchant(), new BackstabEnchant(), new SeasBountyEnchant(), new CallOfTheSeaEnchant(),
                new ExplosiveEnchant(), new InfinityEnchant(), new FireAspectEnchant(), new IceAspectEnchant(), new AdrenalineEnchant(),
                new BlessingOfNatureEnchant());
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
        PluginManager pluginManager = Bukkit.getPluginManager();
        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, this);
        }
    }
}
