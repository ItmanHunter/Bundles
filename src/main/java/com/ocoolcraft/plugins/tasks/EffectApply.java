package com.ocoolcraft.plugins.tasks;

import com.ocoolcraft.plugins.config.BundleItem;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class EffectApply implements Runnable {

    private JavaPlugin plugin;
    private BundleItem bundleItem;
    private Player player;

    public EffectApply(JavaPlugin plugin, BundleItem bundleItem, Player player) {
        this.bundleItem = bundleItem;
        this.plugin = plugin;
        this.player = player;
    }


    @Override
    public void run() {
        if (bundleItem.getSound() != null) {
            player.getWorld().playSound(player.getLocation(),
                    bundleItem.getSound().getSound(),
                    bundleItem.getSound().getVolume(),
                    bundleItem.getSound().getPitch());
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
