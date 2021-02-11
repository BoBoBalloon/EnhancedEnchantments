package me.boboballoon.enhancedenchantments.enchantments.items.weapons.bows;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.BowEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.BowEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityShootBowEvent;

import java.util.Collections;

public class InfinityEnchant extends BowEnchantment {
    public InfinityEnchant() {
        super("Infinity", 1, EnchantmentTier.MYTHIC, BowEnchantmentTrigger.ON_ARROW_FIRED, Collections.singletonList("When you fire your bow, it will not consume any arrows"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        EntityShootBowEvent shoot = (EntityShootBowEvent) event;
        Player player = (Player) shoot.getEntity();
        shoot.setConsumeItem(false);
        player.updateInventory();
    }
}
