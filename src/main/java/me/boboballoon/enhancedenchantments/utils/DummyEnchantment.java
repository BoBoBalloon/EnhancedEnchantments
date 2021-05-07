package me.boboballoon.enhancedenchantments.utils;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;

public class DummyEnchantment {
    public static final BlankEnchantment DUMMY_ENCHANTMENT = new BlankEnchantment();

    public static void start() {
        try {
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            Enchantment.registerEnchantment(DUMMY_ENCHANTMENT);
            Enchantment.stopAcceptingRegistrations();
        } catch (Exception ignore) {}
    }


    private static class BlankEnchantment extends Enchantment {
        public BlankEnchantment() {
            super(new NamespacedKey(EnhancedEnchantments.getInstance(), "dummy-enchantment"));
        }

        @Override
        public String getName() {
            return "Dummy";
        }

        @Override
        public int getMaxLevel() {
            return 0;
        }

        @Override
        public int getStartLevel() {
            return 0;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR; //meaningless
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return false;
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return false;
        }
    }
}
