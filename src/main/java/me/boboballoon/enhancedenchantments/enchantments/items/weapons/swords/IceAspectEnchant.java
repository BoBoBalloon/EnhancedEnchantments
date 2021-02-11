package me.boboballoon.enhancedenchantments.enchantments.items.weapons.swords;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.ItemEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.SwordEnchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

public class IceAspectEnchant extends SwordEnchantment {
    private final PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW, 60, 1, false, false);

    public IceAspectEnchant() {
        super("Ice Aspect", 1, EnchantmentTier.RARE, ItemEnchantmentTrigger.ON_DAMAGE_DEALT, Collections.singletonList("When you hit an enemy, it will be slowed"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) event;
        if (damageEvent.getEntity() instanceof LivingEntity) {
           LivingEntity entity = (LivingEntity) damageEvent.getEntity();
           entity.addPotionEffect(this.potionEffect);
        }
    }
}
