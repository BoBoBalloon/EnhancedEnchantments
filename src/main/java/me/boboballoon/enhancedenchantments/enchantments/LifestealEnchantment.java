package me.boboballoon.enhancedenchantments.enchantments;

import me.boboballoon.enhancedenchantments.enchantment.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Collections;

public class LifestealEnchantment extends SwordEnchantment {
    public LifestealEnchantment() {
        super("Lifesteal", 3, EnchantmentTier.UNCOMMON, ItemEnchantmentTrigger.ON_DAMAGE_DEALT, Collections.singletonList("Steals the health of another entity"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        EntityDamageByEntityEvent damage = (EntityDamageByEntityEvent) event;
        Player player = (Player) damage.getDamager();
        this.addHealth(player, this.getHealth(enchantment, damage.getFinalDamage()));
    }

    private double getHealth(ActiveEnchantment enchantment, double damage) {
        if (enchantment.getLevel() == 1) {
            return damage * .05;
        } else {
            return damage * ((.1 * enchantment.getLevel()) + .05);
        }
    }

    private void addHealth(Player player, double health) {
        health += player.getHealth();

        if (health > 20) {
            health = 20;
        }

        player.setHealth(health);
    }
}
