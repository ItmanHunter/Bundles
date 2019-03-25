package com.ocoolcraft.plugins.tasks;

import com.ocoolcraft.plugins.config.BundleCommand;
import com.ocoolcraft.plugins.config.BundleItem;
import com.ocoolcraft.plugins.utils.ExpressionUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ConsoleCommandExecutor implements Runnable {

    private JavaPlugin plugin;
    private BundleItem bundleItem;
    private Player player;

    public ConsoleCommandExecutor(JavaPlugin plugin, BundleItem bundleItem, Player player) {
        this.bundleItem = bundleItem;
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run() {
        if (bundleItem.getCommands() != null) {
            for(BundleCommand bundleCommand:bundleItem.getCommands()) {
                if (bundleCommand != null) {
                    CommandSender sender = (bundleCommand.getSender() == "console")?(plugin.getServer().getConsoleSender()):(player);
                    plugin.getServer().dispatchCommand(
                            sender,
                            ExpressionUtil.replaceVars(bundleCommand.getCommand(),player)
                    );
                }
            }
        }
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public BundleItem getBundleItem() {
        return bundleItem;
    }

    public void setBundleItem(BundleItem bundleItem) {
        this.bundleItem = bundleItem;
    }

}
