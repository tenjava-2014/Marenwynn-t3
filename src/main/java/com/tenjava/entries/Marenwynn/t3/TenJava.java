package com.tenjava.entries.Marenwynn.t3;

import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.Marenwynn.t3.data.Data;
import com.tenjava.entries.Marenwynn.t3.listeners.FallDamageListener;

public class TenJava extends JavaPlugin {

    @Override
    public void onEnable() {
        Data.init(this);
        Util.init();
        Effects.init(this);

        getServer().getPluginManager().registerEvents(new FallDamageListener(), this);
    }

}
