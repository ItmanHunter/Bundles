package com.ocoolcraft.plugins;

import com.ocoolcraft.plugins.config.BundleItem;
import com.ocoolcraft.plugins.tasks.ConsoleCommandExecutor;
import com.ocoolcraft.plugins.tasks.DropItemsOnGround;
import com.ocoolcraft.plugins.tasks.EffectApply;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class BundleListener implements Listener {

    private JavaPlugin plugin;

    public BundleListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            BundleItem bundleItem = BundleItem.getBundle(player.getInventory().getItemInMainHand());
            if (bundleItem != null) {
                event.setCancelled(true);
                if (bundleItem.addItemToPlayer(player)) {
                    player.getInventory().remove(player.getInventory().getItemInMainHand());
                }
                Bukkit.getScheduler().runTask(plugin,new EffectApply(plugin,bundleItem,player));
                Bukkit.getScheduler().runTask(plugin,new ConsoleCommandExecutor(plugin,bundleItem,player));
            }
        }
    }

}
