package com.ocoolcraft.plugins;

import com.ocoolcraft.plugins.config.BundleItem;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BundleListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            BundleItem bundleItem = BundleItem.getBundle(player.getItemInHand());
            if (bundleItem != null) {
                if (bundleItem.getSound() != null) {
                    player.getWorld().playSound(player.getLocation(),
                            bundleItem.getSound().getSound(),
                            bundleItem.getSound().getVolume(),
                            bundleItem.getSound().getPitch());
                }
                player.getInventory().remove(player.getItemInHand());
                bundleItem.addItemToPlayer(player);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
            Player player = event.getPlayer();
            BundleItem bundleItem = BundleItem.getBundle(player.getItemInHand());
            if (bundleItem != null) {
                player.getInventory().remove(player.getItemInHand());
                bundleItem.addItemToPlayer(player);
                event.setCancelled(true);
            }
    }

}
