package me.boboballoon.enhancedenchantments.enchantments;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.ItemEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.WeaponEnchantment;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class UpUpAndAway extends WeaponEnchantment {

    public UpUpAndAway() {
        super("Up Up And Away", 5, EnchantmentTier.UNCOMMON, ItemEnchantmentTrigger.ON_DAMAGE_DEALT, Arrays.asList("This enchantment will spew out hearts", "And sometimes throw the attacked entity into the sky"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        EntityDamageByEntityEvent damage = (EntityDamageByEntityEvent) event;
        Vector vector = new Vector(0, enchantment.getLevel(), 0);
        Entity entity = damage.getEntity();
        entity.setVelocity(vector);
        entity.getLocation().getWorld().spawnParticle(Particle.HEART, entity.getLocation(), 100, .5, .5, .5);
    }
}
