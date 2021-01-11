package me.boboballoon.enhancedenchantments.enchantments;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.UniversalEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.UniversalEnchantmentTrigger;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemDamageEvent;

import java.util.Collections;

public class UnwaveringEnchant extends UniversalEnchantment {
    public UnwaveringEnchant() {
        super("Unwavering", 3, EnchantmentTier.EPIC, UniversalEnchantmentTrigger.ON_DURABILITY_LOSS, Collections.singletonList("Sometimes you do not lose item durability"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        PlayerItemDamageEvent damage = (PlayerItemDamageEvent) event;
        damage.setCancelled(this.cancelled(enchantment));
    }

    private boolean cancelled(ActiveEnchantment enchantment) {
        int level = enchantment.getLevel();
        int chance = this.random.nextInt(4);

        if (level == 1 && chance == 1) {
            return true;
        }

        if (level == 2 && (chance == 1 || chance == 2)) {
            return true;
        }

        return level == 3 && chance != 1;
    }
}
