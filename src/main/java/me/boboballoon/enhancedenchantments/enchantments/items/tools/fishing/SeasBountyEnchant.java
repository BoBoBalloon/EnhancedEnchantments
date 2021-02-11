package me.boboballoon.enhancedenchantments.enchantments.items.tools.fishing;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.FishingEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.FishingRodEnchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SeasBountyEnchant extends FishingRodEnchantment {
    public SeasBountyEnchant() {
        super("Sea's Bounty", 3, EnchantmentTier.RARE, FishingEnchantmentTrigger.ON_FISH, Arrays.asList("When you successfully fish, you will get extra drops", "Extra drops increase per level of this enchantment"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        PlayerFishEvent fish = (PlayerFishEvent) event;

        if (fish.getState() != PlayerFishEvent.State.CAUGHT_FISH) {
            return;
        }

        Item item = (Item) fish.getCaught();
        ItemStack itemStack = item.getItemStack();

        int multiplier = enchantment.getLevel() + 1;

        fish.setExpToDrop(fish.getExpToDrop() * multiplier);

        itemStack.setAmount(itemStack.getAmount() * multiplier);
        item.setItemStack(itemStack);
    }
}
