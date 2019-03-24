package com.ocoolcraft.plugins.utils;

import org.bukkit.ChatColor;

public class ColorUtil {

    public static String replaceColors(String command) {
        String newCommand = command;
        for(ChatColor color: ChatColor.values()) {
            newCommand = newCommand.replace("&"+color.getChar(),color + "");
        }
        return newCommand;
    }

}
