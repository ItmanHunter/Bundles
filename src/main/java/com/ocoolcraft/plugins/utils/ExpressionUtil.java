package com.ocoolcraft.plugins.utils;

import org.bukkit.entity.Player;

public class ExpressionUtil {

    public static String replaceVars(String str, Player player) {
        String newStr = str.replace("#name",player.getName());
        return newStr;
    }

}
