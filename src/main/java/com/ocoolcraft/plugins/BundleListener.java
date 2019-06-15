package com.ocoolcraft.plugins;

import com.ocoolcraft.plugins.config.BundleItem;
import com.ocoolcraft.plugins.tasks.ConsoleCommandExecutor;
import com.ocoolcraft.plugins.tasks.EffectApply;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class BundleListener implements Listener {

    private JavaPlugin plugin;

    public BundleListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            BundleItem bundleItem = BundleItem.getBundle(player.getItemInHand());
            if (bundleItem != null) {
                event.setCancelled(true);
                if (bundleItem.addItemToPlayer(player)) {
                    if (player.getItemInHand().getAmount() == 1) {
                        player.getInventory().setItem(player.getInventory().getHeldItemSlot() ,null);
                    } else {
                        player.getItemInHand().setAmount(player.getItemInHand().getAmount()-1);
                    }
                    Bukkit.getScheduler().runTask(plugin,new EffectApply(plugin,bundleItem,player));
                    Bukkit.getScheduler().runTask(plugin,new ConsoleCommandExecutor(plugin,bundleItem,player));
                }
            }
        }
    }

}
