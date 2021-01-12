package me.boboballoon.enhancedenchantments.enchantments;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.ItemEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.SwordEnchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Arrays;

public class BackstabEnchant extends SwordEnchantment {
    public BackstabEnchant() {
        super("Backstab", 2, EnchantmentTier.MYTHIC, ItemEnchantmentTrigger.ON_DAMAGE_DEALT, Arrays.asList("Deal an extra half a heart per level", "Ignores armor"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        EntityDamageByEntityEvent damage = (EntityDamageByEntityEvent) event;
        if (damage.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) damage.getEntity();
            entity.setHealth(entity.getHealth() - enchantment.getLevel());
        }
    }
}
