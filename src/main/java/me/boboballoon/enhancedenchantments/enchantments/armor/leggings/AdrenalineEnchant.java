package me.boboballoon.enhancedenchantments.enchantments.armor.leggings;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.LeggingsEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.UniversalEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.events.PlayerNullEvent;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class AdrenalineEnchant extends LeggingsEnchantment {
    private final PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 25, 0, false, false);

    public AdrenalineEnchant() {
        super("Adrenaline", 3, EnchantmentTier.LEGENDARY, UniversalEnchantmentTrigger.EVERY_SECOND, Arrays.asList("When you are on low health, you will gain a speed buff", "The required health to trigger this enchantment will increase with the level"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        Player player = ((PlayerNullEvent) event).getPlayer();
        if (this.giveBuff(player, enchantment.getLevel())) {
            player.addPotionEffect(this.potionEffect);
        }
    }

    private boolean giveBuff(Player player, int level) {
        double percent = player.getHealth() / player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

        if (percent < .15 && level == 1) {
            return true;
        }

        if (percent < .35 && level == 2) {
            return true;
        }

        return percent < .65 && level == 3;
    }
}
