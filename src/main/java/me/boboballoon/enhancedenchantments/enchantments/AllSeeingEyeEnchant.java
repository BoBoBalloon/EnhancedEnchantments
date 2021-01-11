package me.boboballoon.enhancedenchantments.enchantments;

import me.boboballoon.enhancedenchantments.enchantment.*;
import me.boboballoon.enhancedenchantments.events.PlayerNullEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

public class AllSeeingEyeEnchant extends HelmetEnchantment {
    private final PotionEffect potionEffect = new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 0, false, false);

    public AllSeeingEyeEnchant() {
        super("All Seeing Eye", 1, EnchantmentTier.COMMON, UniversalEnchantmentTrigger.EVERY_SECOND, Collections.singletonList("This enchantment will give you permanent night vision"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        Player player = ((PlayerNullEvent) event).getPlayer();
        player.addPotionEffects(Collections.singleton(this.potionEffect));
    }
}
