package me.boboballoon.enhancedenchantments.commands;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.enchantment.Enchantment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetEnchantmentCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> enchantments = new ArrayList<>();
            for (Enchantment enchantment : EnhancedEnchantments.getInstance().getEnchantmentManager().getEnchantments()) {
                enchantments.add(enchantment.getName().replace(' ', '_'));
            }
            return enchantments;
        }

        if (args.length == 2) {
            return Arrays.asList("1", "2", "3");
        }

        return null;
    }
}
