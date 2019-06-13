package com.ocoolcraft.plugins.tasks;

import com.ocoolcraft.plugins.config.BundleItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class DropItemsOnGround implements Runnable {

    private JavaPlugin plugin;
    private BundleItem bundleItem;
    private Player player;
    private List<ItemStack> items;

    public DropItemsOnGround(JavaPlugin plugin, BundleItem bundleItem, Player player,List<ItemStack> items) {
        this.bundleItem = bundleItem;
        this.plugin = plugin;
        this.player = player;
        this.items = items;
    }

    @Override
    public void run() {
        for (ItemStack itemStack:items) {
            player.getWorld().dropItem(
              player.getLocation(),
              itemStack
            );
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
