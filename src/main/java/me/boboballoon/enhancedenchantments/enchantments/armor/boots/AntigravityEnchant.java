package me.boboballoon.enhancedenchantments.enchantments.armor.boots;

import me.boboballoon.enhancedenchantments.enchantment.*;
import me.boboballoon.enhancedenchantments.events.PlayerNullEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

public class AntigravityEnchant extends BootsEnchantment {
    private final PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW_FALLING, 25, 2, false, false);

    public AntigravityEnchant() {
        super("Antigravity", 1, EnchantmentTier.RARE, UniversalEnchantmentTrigger.EVERY_SECOND, Collections.singletonList("This enchantment will give you permanent slow falling"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        Player player = ((PlayerNullEvent) event).getPlayer();
        player.addPotionEffect(this.potionEffect);
    }
}
