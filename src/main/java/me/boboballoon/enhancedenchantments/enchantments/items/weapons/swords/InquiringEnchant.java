package me.boboballoon.enhancedenchantments.enchantments.items.weapons.swords;

import me.boboballoon.enhancedenchantments.enchantment.*;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Collections;

public class InquiringEnchant extends SwordEnchantment {
    public InquiringEnchant() {
        super("Inquiring", 6, EnchantmentTier.LEGENDARY, ItemEnchantmentTrigger.ON_ENTITY_KILLED, Collections.singletonList("When you kill an entity there is a chance you gain extra exp"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        EntityDeathEvent death = (EntityDeathEvent) event;
        death.setDroppedExp(this.getExp(enchantment, death.getDroppedExp()));
    }

    private int getExp(ActiveEnchantment enchantment, int exp) {
        int level = enchantment.getLevel();
        boolean rng = this.random.nextBoolean();

        if (level == 6) {
            return exp * 4;
        }

        if (level == 5 && rng) {
            return exp * 4;
        } else if (level == 5) {
            return exp * 3;
        }

        if (level == 4) {
            return exp * 3;
        }

        if (level == 3 && rng) {
            return exp * 3;
        } else if (level == 3) {
            return exp * 2;
        }

        if (level == 2) {
            return exp * 2;
        }

        if (level == 1 && rng) {
            return exp * 2;
        } else {
            return exp;
        }
    }
}
