package me.boboballoon.enhancedenchantments.enchantments.items.tools.fishing;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.FishingEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.FishingRodEnchantment;
import org.bukkit.entity.FishHook;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.Arrays;

public class CallOfTheSeaEnchant extends FishingRodEnchantment {
    public CallOfTheSeaEnchant() {
        super("Call Of The Sea", 5, EnchantmentTier.RARE, FishingEnchantmentTrigger.ON_FISH, Arrays.asList("The time it takes to get a bite while fishing is decreased", "One second is decreased for each level"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        PlayerFishEvent fish = (PlayerFishEvent) event;

        if (fish.getState() != PlayerFishEvent.State.FISHING) {
            return;
        }

        FishHook hook = fish.getHook();

        int ticks = enchantment.getLevel() * 20;

        hook.setMaxWaitTime(hook.getMaxWaitTime() - ticks);
        hook.setMinWaitTime(hook.getMinWaitTime() + ticks);
    }
}
