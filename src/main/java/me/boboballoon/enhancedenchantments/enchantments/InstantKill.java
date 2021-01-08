package me.boboballoon.enhancedenchantments.enchantments;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.ItemEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.WeaponEnchantment;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class InstantKill extends WeaponEnchantment {
    public InstantKill() {
        super("Instant Kill", 1, EnchantmentTier.MYTHIC, ItemEnchantmentTrigger.ON_DAMAGE_DEALT);
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        EntityDamageByEntityEvent damage = (EntityDamageByEntityEvent) event;
        if (damage.getEntity() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) damage.getEntity();
            livingEntity.setHealth(0);
            livingEntity.getLocation().getWorld().spawnParticle(Particle.CRIT_MAGIC, livingEntity.getLocation(), 100);
        }
    }
}
