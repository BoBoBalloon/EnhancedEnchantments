package me.boboballoon.enhancedenchantments.enchantments;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.ItemEnchantmentTrigger;
import me.boboballoon.enhancedenchantments.enchantment.ToolEnchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class TelepathyEnchant extends ToolEnchantment {
    public TelepathyEnchant() {
        super("Telepathy", 1, EnchantmentTier.COMMON, ItemEnchantmentTrigger.ON_BLOCK_BREAK, Collections.singletonList("Blocks you break go directly into your inventory"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        BlockBreakEvent block = (BlockBreakEvent) event;
        Player player = block.getPlayer();
        player.giveExp(block.getExpToDrop());
        block.setExpToDrop(0);
        block.setDropItems(false);

        for (ItemStack drop : block.getBlock().getDrops(player.getInventory().getItemInMainHand())) {
            if (player.getInventory().firstEmpty() == -1) {
                player.getWorld().dropItemNaturally(block.getBlock().getLocation(), drop);
                continue;
            }

            player.getInventory().addItem(drop);
        }
    }
}
