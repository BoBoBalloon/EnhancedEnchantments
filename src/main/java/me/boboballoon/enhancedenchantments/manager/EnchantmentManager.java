package me.boboballoon.enhancedenchantments.manager;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.enchantment.*;
import me.boboballoon.enhancedenchantments.events.PlayerNullEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/**
 * A class that deals with registering enchantments and listening for triggers
 */
public final class EnchantmentManager implements Listener {
    private final Set<Enchantment> enchantments;

    public EnchantmentManager() {
        this.enchantments = new HashSet<>();

        Bukkit.getScheduler().runTaskTimerAsynchronously(EnhancedEnchantments.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Set<ItemStack> items = new HashSet<>(Arrays.asList(player.getInventory().getArmorContents()));
                items.add(player.getInventory().getItemInMainHand());
                for (ItemStack item : items) {
                    if (item == null || !EnchantmentUtil.isEnchanted(item)) {
                        continue;
                    }

                    for (ActiveEnchantment enchantment : EnchantmentUtil.getEnchantmentHolder(item).getEnchantments()) {
                        //REMOVE DEPRECATED SHIT LATER
                        if (enchantment.getEnchantment().getTrigger() != UniversalEnchantmentTrigger.EVERY_SECOND&&
                                enchantment.getEnchantment().getTrigger() != ArmorEnchantmentTrigger.EVERY_SECOND &&
                                enchantment.getEnchantment().getTrigger() != ItemEnchantmentTrigger.EVERY_SECOND) {
                            continue;
                        }

                        Bukkit.getScheduler().runTask(EnhancedEnchantments.getInstance(), () -> enchantment.getEnchantment().effect(new PlayerNullEvent(player), enchantment));
                    }
                }
            }
        }, 0L, 20L);
    }

    /**
     * Registers enchantments to be loaded/functional in-game
     *
     * @param enchantments all of the enchantments you wish to register
     */
    public void registerEnchantments(Enchantment... enchantments) {
        for (Enchantment enchantment : enchantments) {
            for (Enchantment loadedEnchantment : this.enchantments) {
                if (enchantment.getName().equals(loadedEnchantment.getName())) {
                    EnhancedEnchantments.getInstance().getLogger().log(Level.SEVERE, "An enchantment with the name " + enchantment.getName() + " already exists, registering has been cancelled!");
                    return;
                }
            }
            this.enchantments.add(enchantment);
        }
    }

    /**
     * Unregisters enchantments that are loaded/functional in-game
     *
     * @param enchantments all of the enchantments you wish to unregister
     */
    public void unregisterEnchantments(Enchantment... enchantments) {
        for (Enchantment enchantment : enchantments) {
            this.enchantments.remove(enchantment);
        }
    }

    /**
     * Returns the enchantment that has the name you put
     *
     * @param name the name of the enchantment
     * @return the enchantment, null if enchantment is not registered or does not exist
     */
    public Enchantment getEnchantment(String name) {
        for (Enchantment enchantment : this.enchantments) {
            if (enchantment.getName().equals(name)) {
                return enchantment;
            }
        }
        return null;
    }

    /**
     * Returns all registered enchantments from the specified tier
     *
     * @param tier the specified tier
     * @return all registered enchantments from the specified tier
     */
    public Set<Enchantment> getEnchantments(EnchantmentTier tier) {
        Set<Enchantment> enchantments = new HashSet<>();
        for (Enchantment enchantment : this.enchantments) {
            if (enchantment.getTier() == tier) {
                enchantments.add(enchantment);
            }
        }
        return enchantments;
    }

    /**
     * Returns a copy set of all active enchantments
     *
     * @return a copy set of all active enchantments
     */
    public Set<Enchantment> getEnchantments() {
        return new HashSet<>(this.enchantments);
    }

    //Listeners

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!EnchantmentUtil.isEnchanted(item)) {
            return;
        }

        for (ActiveEnchantment enchantment : EnchantmentUtil.getEnchantmentHolder(item).getEnchantments()) {
            if (!(enchantment.getEnchantment() instanceof ToolEnchantment)) {
                continue;
            }

            if (enchantment.getEnchantment().getTrigger() != ItemEnchantmentTrigger.ON_BLOCK_BREAK) {
                continue;
            }

            enchantment.getEnchantment().effect(event, enchantment);
        }
    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {
        if (event.isCancelled()) {
            return;
        }

        Player player = event.getPlayer();
        Set<ItemStack> items = new HashSet<>();
        items.add(player.getInventory().getItemInMainHand());
        items.addAll(Arrays.asList(player.getInventory().getArmorContents()));

        for (ItemStack item : items) {
            if (item == null) {
                continue;
            }

            if (!EnchantmentUtil.isEnchanted(item)) {
                continue;
            }

            for (ActiveEnchantment enchantment : EnchantmentUtil.getEnchantmentHolder(item).getEnchantments()) {
                EnchantmentTrigger trigger = enchantment.getEnchantment().getTrigger();

                if (trigger != UniversalEnchantmentTrigger.ON_DURABILITY_LOSS) {
                    continue;
                }

                enchantment.getEnchantment().effect(event, enchantment);
            }
        }
    }

    @EventHandler
    public void onPlayerItemBreak(PlayerItemBreakEvent event) {
        ItemStack item = event.getBrokenItem();

        if (!EnchantmentUtil.isEnchanted(item)) {
            return;
        }

        for (ActiveEnchantment enchantment : EnchantmentUtil.getEnchantmentHolder(item).getEnchantments()) {
            EnchantmentTrigger trigger = enchantment.getEnchantment().getTrigger();

            if (trigger != UniversalEnchantmentTrigger.ON_ITEM_BREAK) {
                continue;
            }

            enchantment.getEnchantment().effect(event, enchantment);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();

        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item == null) {
                continue;
            }

            if (!EnchantmentUtil.isEnchanted(item)) {
                continue;
            }

            for (ActiveEnchantment enchantment : EnchantmentUtil.getEnchantmentHolder(item).getEnchantments()) {
                if (!(enchantment.getEnchantment() instanceof ArmorEnchantment)) {
                    continue;
                }

                if (enchantment.getEnchantment().getTrigger() != ArmorEnchantmentTrigger.ON_DAMAGE_TAKEN) {
                    continue;
                }

                enchantment.getEnchantment().effect(event, enchantment);
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getDamager();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!EnchantmentUtil.isEnchanted(item)) {
            return;
        }

        for (ActiveEnchantment enchantment : EnchantmentUtil.getEnchantmentHolder(item).getEnchantments()) {
            if (!(enchantment.getEnchantment() instanceof WeaponEnchantment)) {
                continue;
            }

            if (enchantment.getEnchantment().getTrigger() != ItemEnchantmentTrigger.ON_DAMAGE_DEALT) {
                continue;
            }

            enchantment.getEnchantment().effect(event, enchantment);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Player player = entity.getKiller();

        if (player == null) {
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();

        if (!EnchantmentUtil.isEnchanted(item)) {
            return;
        }

        for (ActiveEnchantment enchantment : EnchantmentUtil.getEnchantmentHolder(item).getEnchantments()) {
            if (!(enchantment.getEnchantment() instanceof WeaponEnchantment)) {
                continue;
            }

            if (enchantment.getEnchantment().getTrigger() != ItemEnchantmentTrigger.ON_ENTITY_KILLED) {
                continue;
            }

            enchantment.getEnchantment().effect(event, enchantment);
        }
    }
}
