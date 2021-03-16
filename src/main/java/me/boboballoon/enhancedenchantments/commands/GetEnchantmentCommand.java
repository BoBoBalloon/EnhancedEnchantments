package me.boboballoon.enhancedenchantments.commands;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.enchantment.EnchantedBook;
import me.boboballoon.enhancedenchantments.enchantment.Enchantment;
import me.boboballoon.enhancedenchantments.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Internal command to get a custom enchantment in-game
 */
public class GetEnchantmentCommand implements CommandExecutor, TabCompleter {

    public GetEnchantmentCommand() {
        PluginCommand getEnchantmentCommand = Bukkit.getPluginCommand("getenchantment");
        getEnchantmentCommand.setExecutor(this);
        getEnchantmentCommand.setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("enhancedenchantments.getenchantment")) {
            sender.sendMessage(TextUtil.format("&r&cYou do not have permission to execute this command!"));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(TextUtil.format("&r&cYou have entered improper arguments to execute this command!"));
            TextUtil.format("&r&c/getenchantment <enchantment_name> <level> <player>");
            return false;
        }

        Enchantment enchantment = EnhancedEnchantments.getInstance().getEnchantmentManager().getEnchantment(args[0].replace('_', ' '));

        if (enchantment == null) {
            sender.sendMessage(TextUtil.format("&r&cYou have entered an invalid enchantment!"));
            return false;
        }

        int level;
        try {
            level = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage(TextUtil.format("&r&cYou have entered an invalid level!"));
            return false;
        }

        EnchantedBook book = new EnchantedBook(enchantment, level);

        Player player;

        if (args.length >= 3) {
            player = Bukkit.getPlayerExact(args[2]);
        } else if (sender instanceof Player) {
            player = (Player) sender;
        } else {
            sender.sendMessage(TextUtil.format("&r&cYou must enter a valid player!"));
            sender.sendMessage(TextUtil.format("&r&c/getenchantment <enchantment_name> <level> <player>"));
            return false;
        }

        if (player == null) {
            sender.sendMessage(TextUtil.format("&r&cYou must enter a valid player!"));
            return false;
        }

        player.getInventory().addItem(book.getBook());

        player.sendMessage(TextUtil.format("&r&aYou have been given " + enchantment.getTier().getColor() + enchantment.getName() + "&r&a!"));

        if (!(sender instanceof Player) || !((Player) sender).getUniqueId().equals(player.getUniqueId())) {
            sender.sendMessage(TextUtil.format("&r&aYou have given " + player.getName() + " " + enchantment.getTier().getColor() + enchantment.getName() + "&r&a!"));
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return this.getEnchantmentNames(args[0]);
        }

        if (args.length == 2) {
            return Arrays.asList("1", "2", "3");
        }

        if (args.length == 3) {
            return null;
        }

        return Collections.emptyList();
    }

    private List<String> getEnchantmentNames(String arg) {
        List<String> enchantments = new ArrayList<>();

        for (Enchantment enchantment : EnhancedEnchantments.getInstance().getEnchantmentManager().getEnchantments()) {
            String name = enchantment.getName().replace(' ', '_');

            if (name.length() < arg.length() ||
                    !name.substring(0, arg.length()).equalsIgnoreCase(arg)) {
                continue;
            }

            enchantments.add(name);
        }

        return enchantments;
    }
}
