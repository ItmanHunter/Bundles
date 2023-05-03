package com.ocoolcraft.plugins.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class PersistentDataHolderUtil {

    public static JavaPlugin plugin;

    public static String getData(PersistentDataHolder persistentDataHolder, String key) {
        return persistentDataHolder.getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }

    public static void setData(PersistentDataHolder persistentDataHolder, String key, String value) {
        persistentDataHolder.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.STRING, value);
    }

}
