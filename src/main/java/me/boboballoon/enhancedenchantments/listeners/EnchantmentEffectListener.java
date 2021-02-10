package me.boboballoon.enhancedenchantments.listeners;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.enchantment.*;
import me.boboballoon.enhancedenchantments.events.PlayerNullEvent;
import me.boboballoon.enhancedenchantments.manager.EnchantmentUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class EnchantmentEffectListener implements Listener {
    private final Map<UUID, EnchantmentHolder> arrows;

    public EnchantmentEffectListener() {
        this.arrows = new HashMap<>();

        Bukkit.getScheduler().runTaskTimerAsynchronously(EnhancedEnchantments.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Set<ItemStack> items = new HashSet<>(Arrays.asList(player.getInventory().getArmorContents()));
                items.add(player.getInventory().getItemInMainHand());
                for (ItemStack item : items) {
                    if (!EnchantmentUtil.isEnchanted(item)) {
                        continue;
                    }

                    EnchantmentHolder holder = EnchantmentUtil.getEnchantmentHolder(item);

                    if (holder == null) {
                        return;
                    }

                    for (ActiveEnchantment enchantment : holder.getEnchantments()) {
                        if (enchantment.getEnchantment().getTrigger() != UniversalEnchantmentTrigger.EVERY_SECOND) {
                            continue;
                        }

                        Bukkit.getScheduler().runTask(EnhancedEnchantments.getInstance(), () -> enchantment.getEnchantment().effect(new PlayerNullEvent(player), enchantment));
                    }
                }
            }
        }, 0L, 20L);
    }

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

        EnchantmentHolder holder = EnchantmentUtil.getEnchantmentHolder(item);

        if (holder == null) {
            return;
        }

        for (ActiveEnchantment enchantment : holder.getEnchantments()) {
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

            EnchantmentHolder holder = EnchantmentUtil.getEnchantmentHolder(item);

            if (holder == null) {
                return;
            }

            for (ActiveEnchantment enchantment : holder.getEnchantments()) {
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

        EnchantmentHolder holder = EnchantmentUtil.getEnchantmentHolder(item);

        if (holder == null) {
            return;
        }

        for (ActiveEnchantment enchantment : holder.getEnchantments()) {
            if (enchantment.getEnchantment().getTrigger() != UniversalEnchantmentTrigger.ON_ITEM_BREAK) {
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

            EnchantmentHolder holder = EnchantmentUtil.getEnchantmentHolder(item);

            if (holder == null) {
                return;
            }

            for (ActiveEnchantment enchantment : holder.getEnchantments()) {
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

        EnchantmentHolder holder = EnchantmentUtil.getEnchantmentHolder(item);

        if (holder == null) {
            return;
        }

        for (ActiveEnchantment enchantment : holder.getEnchantments()) {
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

        EnchantmentHolder holder = EnchantmentUtil.getEnchantmentHolder(item);

        if (holder == null) {
            return;
        }

        for (ActiveEnchantment enchantment : holder.getEnchantments()) {
            if (enchantment.getEnchantment().getTrigger() != ItemEnchantmentTrigger.ON_ENTITY_KILLED) {
                continue;
            }

            enchantment.getEnchantment().effect(event, enchantment);
        }
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.isCancelled()) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!EnchantmentUtil.isEnchanted(item)) {
            return;
        }

        EnchantmentHolder holder = EnchantmentUtil.getEnchantmentHolder(item);

        if (holder == null) {
            return;
        }

        for (ActiveEnchantment enchantment : holder.getEnchantments()) {
            if (enchantment.getEnchantment().getTrigger() != FishingEnchantmentTrigger.ON_FISH) {
                continue;
            }

            enchantment.getEnchantment().effect(event, enchantment);
        }
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        ItemStack item = event.getBow();

        if (!EnchantmentUtil.isEnchanted(item)) {
            return;
        }

        EnchantmentHolder holder = EnchantmentUtil.getEnchantmentHolder(item);

        if (holder == null) {
            return;
        }

        for (ActiveEnchantment enchantment : holder.getEnchantments()) {
            if (enchantment.getEnchantment().getTrigger() != BowEnchantmentTrigger.ON_ARROW_FIRED) {
                continue;
            }

            enchantment.getEnchantment().effect(event, enchantment);
        }

        this.arrows.put(event.getProjectile().getUniqueId(), holder);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getHitEntity() == null || !this.arrows.containsKey(event.getEntity().getUniqueId())) {
            return;
        }

        EnchantmentHolder holder = this.arrows.remove(event.getEntity().getUniqueId());

        for (ActiveEnchantment enchantment : holder.getEnchantments()) {
            if (enchantment.getEnchantment().getTrigger() != BowEnchantmentTrigger.ON_ARROW_HIT) {
                continue;
            }

            enchantment.getEnchantment().effect(event, enchantment);
        }
    }
}
