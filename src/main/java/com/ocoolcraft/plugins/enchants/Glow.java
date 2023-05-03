package com.ocoolcraft.plugins.enchants;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Glow extends Enchantment {

    private static Glow glow;

    public static void createEnchantment(JavaPlugin plugin) {
        glow = new Glow(new NamespacedKey(plugin, "glow"));
    }

    public static Enchantment getEnchantment() {
        return glow;
    }

    public Glow(NamespacedKey key) {
        super(key);
    }

    @Deprecated
    @Override
    public String getName() {
        return "GLOW";
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return false;
    }


}