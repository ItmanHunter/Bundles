package com.ocoolcraft.plugins.utils;

import org.bukkit.plugin.java.JavaPlugin;

public class Logger {

    public static JavaPlugin plugin;

    public static void log(String message) {
        plugin.getLogger().info(message);
    }

    public static void log(Exception ex) {
        plugin.getLogger().info(ex.getMessage());
    }
}
