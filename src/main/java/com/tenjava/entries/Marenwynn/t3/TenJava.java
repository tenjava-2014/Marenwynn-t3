package com.tenjava.entries.Marenwynn.t3;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.Marenwynn.t3.data.Data;
import com.tenjava.entries.Marenwynn.t3.listeners.FallDamageListener;
import com.tenjava.entries.Marenwynn.t3.listeners.ItemUseListener;
import com.tenjava.entries.Marenwynn.t3.listeners.PlayerListener;

public class TenJava extends JavaPlugin {

    @Override
    public void onEnable() {
        Data.init(this);
        Util.init();
        Effects.init(this);

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new ItemUseListener(), this);
        pm.registerEvents(new FallDamageListener(), this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        Data.saveAllPlayers();
    }

}
