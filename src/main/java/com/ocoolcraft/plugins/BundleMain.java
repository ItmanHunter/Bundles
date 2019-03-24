package com.ocoolcraft.plugins;

import com.ocoolcraft.plugins.comand.CreateBundle;
import com.ocoolcraft.plugins.comand.GetBundle;
import com.ocoolcraft.plugins.enchants.Glow;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class BundleMain extends JavaPlugin {

    @Override
    public void onEnable() {
        Glow.createEnchantment(this);
        getDataFolder().mkdirs();
        registerGlow();
        getCommand("cbundle").setExecutor(new CreateBundle());
        getCommand("getbundle").setExecutor(new GetBundle());
        getServer().getPluginManager().registerEvents(new BundleListener(this), this);
        getLogger().info("Enabled BundleMain");
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
            e.printStackTrace();
        }
        try {
            Enchantment.registerEnchantment(Glow.getEnchantment());
        }
        catch (IllegalArgumentException e){
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
