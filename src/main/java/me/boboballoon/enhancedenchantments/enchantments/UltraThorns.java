package me.boboballoon.enhancedenchantments.enchantments;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.ArmorEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.ArmorEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Collections;

public class UltraThorns extends ArmorEnchantment {
    public UltraThorns() {
        super("Ultra Thorns", 1, EnchantmentTier.MYTHIC, ArmorEnchantmentTrigger.ON_DAMAGE_TAKEN, Collections.singletonList("Insta kill attacker when hit"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        if (!(event instanceof EntityDamageByEntityEvent)) {
            return;
        }

        EntityDamageByEntityEvent damage = (EntityDamageByEntityEvent) event;

        if (!(damage.getDamager() instanceof LivingEntity)) {
            return;
        }

        LivingEntity entity = (LivingEntity) damage.getDamager();
        entity.setHealth(0);
    }
}
