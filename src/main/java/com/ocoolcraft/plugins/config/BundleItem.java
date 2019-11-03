package com.ocoolcraft.plugins.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ocoolcraft.plugins.enchants.Glow;
import com.ocoolcraft.plugins.utils.ColorUtil;
import com.ocoolcraft.plugins.utils.FileUtils;
import com.ocoolcraft.plugins.utils.HiddenStringUtil;
import com.ocoolcraft.plugins.utils.InventoryUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BundleItem {

    private static String dataFolderPath = "plugins/Bundle/";

    private String name, displayName;
    private String material;
    private String[] description;
    private String inventory;
    private String permission;
    private BundleCommand[] commands;

    private BundleSound sound;

    private boolean enchant;

    private ItemStack[] realInventory = null;
    private int noOfItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public static BundleItem loadBundle(String name) {
        String fileName = dataFolderPath + File.separator + name + ".json";
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(FileUtils.readStringFromFile(fileName),BundleItem.class);
    }

    public static BundleItem createBundle(String name,Player player) {
        BundleItem bundleItem = new BundleItem();
        bundleItem.setInventory(InventoryUtil.toBase64(player.getInventory()));
        bundleItem.setDisplayName(name);
        bundleItem.setSound(BundleSound.getDefaultSound());
        bundleItem.setName(name);
        bundleItem.setEnchant(false);
        bundleItem.setDescription(new String[] {HiddenStringUtil.encodeString("id:"+name)});
        bundleItem.setMaterial(Material.STONE_AXE.name());
        bundleItem.setCommands(new BundleCommand[10]);
        bundleItem.saveBundle();
        return bundleItem;
    }

    public void saveBundle() {
        String fileName = dataFolderPath + File.separator + name + ".json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String bundleJson = gson.toJson(this,BundleItem.class);
        FileUtils.writeStringToFile(bundleJson,fileName);
    }

    public static BundleItem getBundle(ItemStack itemStack) {
        if (itemStack == null || itemStack.getItemMeta() == null) {
            return null;
        }
        List<String> list = itemStack.getItemMeta().getLore();
        if (list == null || list.size() == 0) {
            return null;
        }
        for (String line:list) {
            if (HiddenStringUtil.hasHiddenString(line)) {
                String data = HiddenStringUtil.extractHiddenString(line);
                if (data.startsWith("id:")) {
                    String name = data.split(":")[1];
                    return loadBundle(name);
                }
            }
        }
        return null;
    }

    public ItemStack getBundle() {
        Material materialT = Material.STONE_AXE;
        try {
            materialT = Material.getMaterial(material);
        } catch (Exception ex) {
            materialT = Material.STONE_AXE;
        }
        ItemStack itemStack = new ItemStack(materialT,1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (enchant) {
            itemMeta.addEnchant(Glow.getEnchantment(), 1, true);
        }
        itemMeta.setDisplayName(ColorUtil.replaceColors(displayName));
        itemMeta.setLore(getLore());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private List<String> getLore() {
        List<String> lore = new ArrayList<String>();
        for(String str:description) {
            if (str != null) {
                lore.add(ColorUtil.replaceColors(str));
            }
        }
        return lore;
    }

    private ItemStack[] getRealInventory() {
        if (realInventory == null) {
            realInventory = InventoryUtil.fromBase64(inventory);
        }
        return realInventory;
    }

    private boolean checkSpace(Player player) {
        int count  = 0, real = 0;
        for(ItemStack item:player.getInventory().getContents()) {
            if (item == null) {
                count++;
            }
        }
        for (ItemStack item:getRealInventory()) {
            if (item != null) {
                real++;
            }
        }
        return (count >= real);
    }

    public boolean addItemToPlayer(Player player) {
        if (!checkSpace(player)) {
            player.sendMessage("No space...");
            return false;
        }
        for(ItemStack itemStack: getRealInventory()) if(itemStack != null) {
                player.getInventory().addItem(itemStack);
                player.updateInventory();
        }
        return true;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isEnchant() {
        return enchant;
    }

    public void setEnchant(boolean enchant) {
        this.enchant = enchant;
    }

    public BundleSound getSound() {
        return sound;
    }

    public void setSound(BundleSound sound) {
        this.sound = sound;
    }

    public BundleCommand[] getCommands() {
        return commands;
    }

    public void setCommands(BundleCommand[] commands) {
        this.commands = commands;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
