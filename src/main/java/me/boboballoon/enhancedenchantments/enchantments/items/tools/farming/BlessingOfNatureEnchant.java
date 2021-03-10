package me.boboballoon.enhancedenchantments.enchantments.items.tools.farming;

import me.boboballoon.enhancedenchantments.enchantment.ActiveEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.EnchantmentTier;
import me.boboballoon.enhancedenchantments.enchantment.HoeEnchantment;
import me.boboballoon.enhancedenchantments.enchantment.ItemEnchantmentTrigger;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class BlessingOfNatureEnchant extends HoeEnchantment {
    private final List<Material> crops = Arrays.asList(Material.SWEET_BERRY_BUSH, Material.WHEAT, Material.CARROTS, Material.MELON_STEM, Material.PUMPKIN_STEM, Material.BEETROOTS, Material.POTATOES);

    public BlessingOfNatureEnchant() {
        super("Blessing Of Nature", 3, EnchantmentTier.RARE, ItemEnchantmentTrigger.ON_BLOCK_BREAK, Arrays.asList("When you break a crop, another random crop is dropped as well", "Will increase extra received by one per level of this enchantment"));
    }

    @Override
    public void effect(Event event, ActiveEnchantment enchantment) {
        BlockBreakEvent blockBreakEvent = (BlockBreakEvent) event;
        Block block = blockBreakEvent.getBlock();
        Material material = block.getType();

        if (!this.crops.contains(material)) {
            return;
        }

        if (!(block.getState().getBlockData() instanceof Ageable)) {
            return;
        }

        Ageable crop = (Ageable) block.getState().getBlockData();

        if (crop.getAge() != crop.getMaximumAge()) {
            return;
        }

        int index = this.random.nextInt(this.crops.size());
        int amount = this.random.nextInt(3) + enchantment.getLevel() + 1;

        blockBreakEvent.getPlayer().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), new ItemStack(this.toItem(this.crops.get(index)), amount));
    }

    private Material toItem(Material material) {
        if (material == Material.SWEET_BERRY_BUSH) {
            return Material.SWEET_BERRIES;
        }

        if (material == Material.CARROTS) {
            return Material.CARROT;
        }

        if (material == Material.MELON_STEM) {
            return Material.MELON_SLICE;
        }

        if (material == Material.PUMPKIN_STEM) {
            return Material.PUMPKIN;
        }

        if (material == Material.BEETROOTS) {
            return Material.BEETROOT;
        }

        if (material == Material.POTATOES) {
            return Material.POTATO;
        }

        return material;
    }
}
