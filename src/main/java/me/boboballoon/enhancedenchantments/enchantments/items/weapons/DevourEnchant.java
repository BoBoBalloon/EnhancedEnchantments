package me.boboballoon.enhancedenchantments.enchantments.items.weapons;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.ItemEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.WeaponEnchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Arrays;

public class DevourEnchant extends WeaponEnchantment {
    public DevourEnchant() {
        super("Devour", 10, EnchantmentTier.LEGENDARY, ItemEnchantmentTrigger.ON_DAMAGE_DEALT, Arrays.asList("When a player hits another player", "There is a chance that their food bars will be stolen"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        EntityDamageByEntityEvent damage = (EntityDamageByEntityEvent) event;
        if (damage.getEntity() instanceof Player && damage.getDamager() instanceof Player) {
            Player player = (Player) damage.getDamager();
            Player target = (Player) damage.getEntity();

            int hunger = enchantment.getLevel() * 2;
            this.setFoodLevel(player, hunger);
            this.setFoodLevel(target, -hunger);
        }
    }

    private void setFoodLevel(Player player, int hunger) {
        hunger += player.getFoodLevel();
        if (hunger > 20) {
            hunger = 20;
        } else if (hunger < 0) {
            hunger = 0;
        }
        player.setFoodLevel(hunger);
    }
}
