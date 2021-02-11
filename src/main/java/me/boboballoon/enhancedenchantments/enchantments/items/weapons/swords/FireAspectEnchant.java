package me.boboballoon.enhancedenchantments.enchantments.items.weapons.swords;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.ItemEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.SwordEnchantment;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Arrays;

public class FireAspectEnchant extends SwordEnchantment {
    public FireAspectEnchant() {
        super("Fire Aspect", 3, EnchantmentTier.UNCOMMON, ItemEnchantmentTrigger.ON_DAMAGE_DEALT, Arrays.asList("When you hit an enemy, it will be set on fire", "For one second per level of this enchantment"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) event;
        Entity entity = damageEvent.getEntity();
        entity.setFireTicks((enchantment.getLevel() * 20) + 20 + entity.getFireTicks());
    }
}
