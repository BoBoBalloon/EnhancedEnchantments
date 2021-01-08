package me.boboballoon.enhancedenchantments.commands;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.enchantment.EnchantedBook;
import me.boboballoon.enhancedenchantments.enchantment.Enchantment;
import me.boboballoon.enhancedenchantments.utils.TextUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetEnchantmentCommand implements CommandExecutor {
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
}
