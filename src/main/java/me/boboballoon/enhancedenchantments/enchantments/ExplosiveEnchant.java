package me.boboballoon.enhancedenchantments.enchantments;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.BowEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.BowEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Collections;

public class ExplosiveEnchant extends BowEnchantment {
    public ExplosiveEnchant() {
        super("Explosive", 6, EnchantmentTier.EPIC, BowEnchantmentTrigger.ON_ARROW_HIT, Collections.singletonList("When the arrow hits an entity, the target explodes"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        ProjectileHitEvent hit = (ProjectileHitEvent) event;
        if (!(hit.getHitEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity entity = (LivingEntity) hit.getHitEntity();

        entity.damage(enchantment.getLevel());
        entity.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, entity.getLocation().clone().add(0, 1, 0), 1);
    }
}
