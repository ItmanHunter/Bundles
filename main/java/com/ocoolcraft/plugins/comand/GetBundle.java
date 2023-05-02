package com.ocoolcraft.plugins.comand;

import com.ocoolcraft.plugins.config.BundleItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetBundle implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command0, String label, String args[]) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("bundle.getbundle")) {
                if (args.length != 1) {
                    player.sendMessage("usage /getbundle <name>");
                    return true;
                }
                String name = args[0];
                BundleItem item = BundleItem.loadBundle(name);
                if (item != null) {
                    if (player.hasPermission("bundle.getbundle." + name)) {
                        player.sendMessage(ChatColor.BLUE + " BundleMain " + name + " got!!");
                        player.getInventory().addItem(item.getBundle());
                    } else {
                        player.sendMessage(ChatColor.RED + " No permission ");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + " No such bundle!! ");
                }
            } else {
                player.sendMessage(ChatColor.BLUE + " No permission ");
            }
        }
        return true;
    }

}
