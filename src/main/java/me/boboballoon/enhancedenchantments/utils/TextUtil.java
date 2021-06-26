package me.boboballoon.enhancedenchantments.utils;

import org.bukkit.ChatColor;

public final class TextUtil {
    /**
     * Private constructor to prevent initialization
     */
    private TextUtil() {}

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
