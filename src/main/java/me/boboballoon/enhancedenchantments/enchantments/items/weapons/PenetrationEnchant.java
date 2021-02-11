package me.boboballoon.enhancedenchantments.enchantments.items.weapons;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.ItemEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.WeaponEnchantment;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Arrays;

public class PenetrationEnchant extends WeaponEnchantment {
    public PenetrationEnchant() {
        super("Penetration", 5, EnchantmentTier.MYTHIC, ItemEnchantmentTrigger.ON_DAMAGE_DEALT, Arrays.asList("Every level has an extra 10% chance", "To ignore armor completely"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        EntityDamageByEntityEvent damage = (EntityDamageByEntityEvent) event;

        if (this.goodCause(damage) && this.isCancelled(enchantment.getLevel())) {
            damage.setDamage(EntityDamageEvent.DamageModifier.ARMOR, 0);
        }
    }

    private boolean goodCause(EntityDamageByEntityEvent event) {
        return event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK ||
                event.getCause() == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK ||
                event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK;
    }

    private boolean isCancelled(int level) {
        int random = this.random.nextInt(10);
        return level - 1 >= random;
    }
}
