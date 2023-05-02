package com.ocoolcraft.plugins.comand;

import com.ocoolcraft.plugins.config.BundleItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateBundle implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command0, String label, String args[]) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("bundle.cbundle")) {
                if (args.length != 1) {
                    player.sendMessage("usage /createbundle <name>");
                    return true;
                }
                String name = args[0];
                BundleItem.createBundle(name,player);
                player.sendMessage(ChatColor.BLUE + " BundleMain created success");
            } else {
                player.sendMessage(ChatColor.BLUE + " No permission ");
            }
        }
        return true;
    }

}
