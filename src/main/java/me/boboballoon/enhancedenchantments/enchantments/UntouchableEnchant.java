package me.boboballoon.enhancedenchantments.enchantments;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.ArmorEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.ChestplateEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Collections;

public class UntouchableEnchant extends ChestplateEnchantment {
    public UntouchableEnchant() {
        super("Untouchable", 5, EnchantmentTier.MYTHIC, ArmorEnchantmentTrigger.ON_DAMAGE_TAKEN, Collections.singletonList("Damage is reduced while crouching"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        EntityDamageEvent damage = (EntityDamageEvent) event;
        Player player = (Player) damage.getEntity();
        if (player.isSneaking()) {
            damage.setDamage(this.getDamage(enchantment, damage.getFinalDamage()));
        }
    }

    private double getDamage(ActiveEnchantment enchantment, double damage) {
        int level = enchantment.getLevel();

        if (level == 5) {
            return damage * .15;
        }

        if (level == 4) {
            return damage * .35;
        }

        if (level == 3) {
            return damage * .5;
        }

        if (level == 2) {
            return damage * .65;
        }

        if (level == 1) {
            return damage * .85;
        }

        return damage;
    }
}
