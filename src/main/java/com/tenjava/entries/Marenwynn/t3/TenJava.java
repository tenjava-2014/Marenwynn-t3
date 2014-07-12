package com.tenjava.entries.Marenwynn.t3;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.Marenwynn.t3.data.Data;
import com.tenjava.entries.Marenwynn.t3.listeners.CombatListener;
import com.tenjava.entries.Marenwynn.t3.listeners.FallDamageListener;
import com.tenjava.entries.Marenwynn.t3.listeners.ItemUseListener;
import com.tenjava.entries.Marenwynn.t3.listeners.PlayerListener;

public class TenJava extends JavaPlugin {

    @Override
    public void onEnable() {
        Data.init(this);
        Effects.init(this);
        Util.init();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new ItemUseListener(), this);
        pm.registerEvents(new FallDamageListener(), this);
        pm.registerEvents(new CombatListener(), this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        Data.saveAllPlayers();
    }

}
