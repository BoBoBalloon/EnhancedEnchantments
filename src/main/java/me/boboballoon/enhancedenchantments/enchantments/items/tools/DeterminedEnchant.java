package me.boboballoon.enhancedenchantments.enchantments.items.tools;

import me.boboballoon.enhancedenchantments.enchantment.*;
import me.boboballoon.enhancedenchantments.events.PlayerNullEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

public class DeterminedEnchant extends ToolEnchantment {
    public DeterminedEnchant() {
        super("Determined", 4, EnchantmentTier.EPIC, UniversalEnchantmentTrigger.EVERY_SECOND, Collections.singletonList("This enchantment will give you permanent haste"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        Player player = ((PlayerNullEvent) event).getPlayer();

        PotionEffect potionEffect = new PotionEffect(PotionEffectType.FAST_DIGGING, 25, enchantment.getLevel() - 1, false, false);

        player.addPotionEffect(potionEffect);
    }
}
