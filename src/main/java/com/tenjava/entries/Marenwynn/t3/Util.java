package com.tenjava.entries.Marenwynn.t3;

import java.util.Random;

import org.bukkit.ChatColor;

public class Util {

    private static Random rng;

    public static void init() {
        rng = new Random();
    }

    public static Random getRandom() {
        return rng;
    }

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
