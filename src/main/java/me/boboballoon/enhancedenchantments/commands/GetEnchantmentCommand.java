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
        if (!(sender instanceof Player)) {
            sender.sendMessage(TextUtil.format("&r&cOnly players can execute this command!"));
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("enhancedenchantments.getenchantment")) {
            player.sendMessage(TextUtil.format("&r&cYou do not have permission to execute this command!"));
            return false;
        }

        if (args.length != 2) {
            player.sendMessage(TextUtil.format("&r&cYou have entered improper arguments to execute this command!"));
            return false;
        }

        Enchantment enchantment = EnhancedEnchantments.getInstance().getEnchantmentManager().getEnchantment(args[0].replace('_', ' '));

        if (enchantment == null) {
            player.sendMessage(TextUtil.format("&r&cYou have entered an invalid enchantment!"));
            return false;
        }

        int level;
        try {
            level = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(TextUtil.format("&r&cYou have entered an invalid level!"));
            return false;
        }

        EnchantedBook book = new EnchantedBook(enchantment, level);

        player.getInventory().addItem(book.getBook());

        player.sendMessage(TextUtil.format("&r&aYou have been given " + enchantment.getTier().getColor() + enchantment.getName() + "&r&a!"));

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

        return null;
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
