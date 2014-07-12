package com.tenjava.entries.Marenwynn.t3;

import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.tenjava.entries.Marenwynn.t3.data.Msg;

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

    @SuppressWarnings("deprecation")
    public static void bleed(Player p) {
        for (Entity e : p.getNearbyEntities(15D, 15D, 15D))
            if (e instanceof Player)
                ((Player) e).sendBlockChange(p.getLocation(), Material.REDSTONE_WIRE, (byte) 0);
    }

    public static void playerYell(Player p, Msg msg, double severity) {
        for (Entity e : p.getNearbyEntities(15 + severity, 15 + severity, 15 + severity))
            if (e instanceof Player)
                Msg.YELL.sendTo((Player) e, Msg.YELL_BROKEN_LEG, p.getName());

        Msg.YELL.sendTo(p, Msg.YELL_BROKEN_LEG, p.getName());
    }

    public static ItemStack setItemNameAndLore(ItemStack item, String name, List<String> lore) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(color(name));

        if (lore != null)
            im.setLore(lore);

        item.setItemMeta(im);
        return item;
    }

}
