package me.boboballoon.enhancedenchantments.enchantments;

import me.boboballoon.enhancedenchantments.enchantment.*;
import me.boboballoon.enhancedenchantments.events.PlayerNullEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

public class AntiGravityEnchant extends BootsEnchantment {
    private final PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW_FALLING, 25, 2, false, false);

    public AntiGravityEnchant() {
        super("Antigravity", 1, EnchantmentTier.RARE, UniversalEnchantmentTrigger.EVERY_SECOND, Collections.singletonList("This enchantment will give you permanent slow falling"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        Player player = ((PlayerNullEvent) event).getPlayer();
        player.addPotionEffects(Collections.singleton(this.potionEffect));
    }
}
