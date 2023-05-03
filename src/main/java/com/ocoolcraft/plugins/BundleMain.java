package com.ocoolcraft.plugins;

import com.ocoolcraft.plugins.comand.CreateBundle;
import com.ocoolcraft.plugins.comand.GetBundle;
import com.ocoolcraft.plugins.enchants.Glow;
import com.ocoolcraft.plugins.utils.Logger;
import com.ocoolcraft.plugins.utils.PersistentDataHolderUtil;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class BundleMain extends JavaPlugin {

    @Override
    public void onEnable() {
        registerUtils();
        getDataFolder().mkdirs();
        registerGlow();
        getCommand("cbundle").setExecutor(new CreateBundle());
        getCommand("getbundle").setExecutor(new GetBundle());
        getServer().getPluginManager().registerEvents(new BundleListener(this), this);
        Logger.log("Enabled BundleMain");
    }

    private void registerUtils() {
        PersistentDataHolderUtil.plugin = this;
        Logger.plugin = this;
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled BundleMain");
    }

    public void registerGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            Logger.log(e);
        }
        try {
            Glow.createEnchantment(this);
            Enchantment.registerEnchantment(Glow.getEnchantment());
        }
        catch (IllegalArgumentException e){
            Logger.log(e);
        }
        catch(Exception e){
            Logger.log(e);
        }
    }

}
